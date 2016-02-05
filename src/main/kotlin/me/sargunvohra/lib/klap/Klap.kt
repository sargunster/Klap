package me.sargunvohra.lib.klap

import me.sargunvohra.lib.cakeparse.api.*
import me.sargunvohra.lib.cakeparse.lexer.TokenInstance
import me.sargunvohra.lib.klap.internal.KlapResult
import me.sargunvohra.lib.klap.annotation.ListArg
import me.sargunvohra.lib.klap.exception.KlapInvalidArgException
import me.sargunvohra.lib.klap.exception.KlapMissingKeyException
import me.sargunvohra.lib.klap.exception.MissingConstructorException
import me.sargunvohra.lib.klap.internal.Arg
import me.sargunvohra.lib.klap.internal.KlapGrammar
import kotlin.reflect.*
import kotlin.reflect.jvm.jvmName

object Klap {

    private fun Iterable<String>.toTokens() = map {
        when {
            it.startsWith("--") -> TokenInstance(KlapGrammar.longFlag, it.substring(2), -1, -1, -1)
            it.startsWith("-") -> TokenInstance(KlapGrammar.shortFlag, it.substring(1), -1, -1, -1)
            else -> TokenInstance(KlapGrammar.word, it, -1, -1, -1)
        }
    }.asSequence()

    private fun Sequence<TokenInstance>.toParsed() = parseToEnd(KlapGrammar.goal).value

    private fun List<Arg>.toInitialResult() = fold(KlapResult()) { total, arg ->
        when (arg) {
            is Arg.FlagArgs -> total.copy(
                    flagArgs = total.flagArgs + arg.keys
            )
            is Arg.ValueArgs -> total.copy(
                    valueArgs = total.valueArgs + arg.pair,
                    flagArgs = total.flagArgs + arg.extraFlags.keys
            )
            is Arg.WordArgs -> total.copy(
                    wordArgs = total.wordArgs + arg.values
            )
        }
    }

    private fun List<Arg>.toResult(flags: Set<String>, keys: Set<String>): KlapResult {
        val initial = toInitialResult()
        var current = initial

        current.valueArgs.forEach {
            val (key, value) = it
            if (!keys.contains(key))
                current = current.copy(
                        flagArgs = current.flagArgs + key,
                        valueArgs = current.valueArgs.toSortedMap().apply { remove(key) },
                        wordArgs = current.wordArgs + value
                )
        }

        current.flagArgs.forEach {
            require(flags.contains(it)) { throw KlapInvalidArgException(if (it.length == 1) "-$it" else "--$it") }
        }

        return current
    }

    fun <T: Any> parseArgs(input: Iterable<String>, target: KClass<T>): T {
        val constructor = target.primaryConstructor
                ?: throw MissingConstructorException(target.simpleName ?: target.jvmName)

        val nameToParameter = constructor.parameters.associate { it.name!! to it }

        val groups = nameToParameter.toList().groupBy { it.second.type }

        val flagToParameter = groups[Boolean::class.defaultType]?.toMap() ?: emptyMap()
        val keyToParameter = groups[String::class.defaultType]?.toMap() ?: emptyMap()
        val listParameter = constructor.parameters.find { it.annotations.any { it.annotationClass == ListArg::class } }

        val parsedResult = input.toTokens().toParsed().toResult(flagToParameter.keys, keyToParameter.keys)

        val args = hashMapOf<KParameter, Any>()

        flagToParameter.values.forEach {
            args[it] = false
        }

        parsedResult.flagArgs.forEach {
            args[flagToParameter[it]!!] = true
        }

        parsedResult.valueArgs.forEach {
            args[keyToParameter[it.key]!!] = it.value
        }

        keyToParameter.values.forEach {
            require(it.isOptional || args.containsKey(it)) { throw KlapMissingKeyException(it.name!!) }
        }

        listParameter?.let {
            args[it] = parsedResult.wordArgs
        } ?: require(parsedResult.wordArgs.isEmpty()) { throw KlapInvalidArgException(parsedResult.wordArgs[0]) }

        return constructor.callBy(args)
    }

    fun <T: Any> parseArgs(input: Array<String>, target: KClass<T>) = parseArgs(input.toList(), target)
}