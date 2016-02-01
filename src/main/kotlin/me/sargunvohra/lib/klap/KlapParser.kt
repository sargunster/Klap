package me.sargunvohra.lib.klap

import java.util.*

class KlapParser(args: Array<out String>) {
    private val args = args.toCollection(LinkedList())

    val remaining: List<String>
        get() = args.toList()

    private fun isFlagSet(key: String): Boolean = args.remove(key)

    private fun getArgValue(key: String): String? {
        var prev: String? = null
        var i = -2
        args.find {
            i++
            if (prev == key) true
            else {
                prev = it
                false
            }
        }?.let {
            args.removeAt(i)
            args.removeAt(i)
            return it
        }
        return null
    }

    private fun getArgListValues(key: String): List<String>? {
        var found = false
        var inList = false

        val result = LinkedList<String>()
        val remaining = LinkedList<String>()

        for (it in args) {
            if (!found && it == key) {
                found = true
                inList = true
            } else if (inList && it.startsWith("-"))
                inList = false
            if (inList)
                result.add(it)
            else
                remaining.add(it)
        }

        return if (found) {
            args.clear()
            remaining.toCollection(args)
            result.drop(1)
        } else null
    }

    fun isFlagSet(vararg keys: String): Boolean {
        keys.forEach {
            if (isFlagSet("-$it") || isFlagSet("--$it"))
                return true
        }
        return false
    }

    fun getArgValue(vararg keys: String): String? {
        keys.forEach {
            getArgValue("-$it")?.let { return it }
            getArgValue("--$it")?.let { return it }
        }
        return null
    }

    fun getArgListValues(vararg keys: String): List<String>? {
        keys.forEach {
            getArgListValues("-$it")?.let { return it }
            getArgListValues("--$it")?.let { return it }
        }
        return null
    }
}