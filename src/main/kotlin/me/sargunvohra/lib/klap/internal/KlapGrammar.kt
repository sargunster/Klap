package me.sargunvohra.lib.klap.internal

import me.sargunvohra.lib.cakeparse.api.*

internal object KlapGrammar {
    // TODO allow token with no pattern in CakeParse
    private const val regexMatchNothing = "(?!)"

    val longFlag = token("flag", regexMatchNothing)

    val shortFlag = token("shortFlag", regexMatchNothing)

    val word = token("word", regexMatchNothing)

    val flags = (longFlag map {
        listOf(it.raw)
    }) or (shortFlag map {
        it.raw.map { it.toString() }
    })

    val wordVal = word map {
        it.raw
    }

    val flagArg = flags map {
        Arg.FlagArgs(keys = it)
    }

    val wordArg = wordVal map {
        Arg.WordArgs(values = listOf(it))
    }

    val valueArg = flags and wordVal map {
        Arg.ValueArgs(
                pair = it.first.last() to it.second,
                extraFlags = Arg.FlagArgs(it.first.dropLast(1))
        )
    }

    val goal = zeroOrMore(valueArg or flagArg or wordArg)
}