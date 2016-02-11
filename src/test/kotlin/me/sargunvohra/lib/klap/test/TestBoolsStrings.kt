package me.sargunvohra.lib.klap.test

import me.sargunvohra.lib.klap.annotation.SingleArg

data class TestBoolsStrings(
        @SingleArg("one")
        val one: Boolean,

        @SingleArg("two")
        val two: Boolean,

        @SingleArg("three")
        val three: String,

        @SingleArg("four")
        val four: String = "four"
) {
    override fun toString() = listOf(one, two, three, four).joinToString(", ")
}