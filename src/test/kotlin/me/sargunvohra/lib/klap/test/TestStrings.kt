package me.sargunvohra.lib.klap.test

import me.sargunvohra.lib.klap.annotation.SingleArg

data class TestStrings(
        @SingleArg("a")
        val a: String,

        @SingleArg("b")
        val b: String = "b",

        @SingleArg("hello", "h")
        val hello: String,

        @SingleArg("world", "w")
        val world: String = "world"
) {
    override fun toString() = listOf(a, b, hello, world).joinToString(", ")
}