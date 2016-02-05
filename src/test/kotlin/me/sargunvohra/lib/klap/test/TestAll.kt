package me.sargunvohra.lib.klap.test

import me.sargunvohra.lib.klap.annotation.ListArg

data class TestAll(
        val b: Boolean,
        val x: String,
        val y: String = "default",

        @ListArg
        val list: List<String>
) {
    override fun toString() = (listOf(b, x, y) + list).joinToString(", ")
}