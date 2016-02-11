package me.sargunvohra.lib.klap.test

import me.sargunvohra.lib.klap.annotation.ListArg
import me.sargunvohra.lib.klap.annotation.SingleArg

data class TestAll(
        @SingleArg("b")
        val b: Boolean,

        @SingleArg("x")
        val x: String,

        @SingleArg("y")
        val y: String = "default",

        @ListArg
        val list: List<String>
) {
    override fun toString() = (listOf(b, x, y) + list).joinToString(", ")
}