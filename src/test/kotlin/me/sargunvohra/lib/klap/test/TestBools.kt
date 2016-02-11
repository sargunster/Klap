package me.sargunvohra.lib.klap.test

import me.sargunvohra.lib.klap.annotation.SingleArg

data class TestBools(
        @SingleArg("a")
        val a: Boolean,

        @SingleArg("b")
        val b: Boolean,

        @SingleArg("c")
        val c: Boolean,

        @SingleArg("d")
        val d: Boolean,

        @SingleArg("hello", "h")
        val hello: Boolean,

        @SingleArg("world", "w")
        val world: Boolean
) {
    override fun toString() = listOf(a, b, c, d, hello, world).joinToString(", ")
}