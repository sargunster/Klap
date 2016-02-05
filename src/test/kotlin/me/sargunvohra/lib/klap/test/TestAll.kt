package me.sargunvohra.lib.klap.test

import me.sargunvohra.lib.klap.annotation.ListArg

data class TestAll(
        val b: Boolean,
        val s1: String,
        val s2: String = "s2",

        @ListArg
        val list: List<String>
) {
    override fun toString() = (listOf(b, s1, s2) + list).joinToString(", ")
}