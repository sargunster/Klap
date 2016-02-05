package me.sargunvohra.lib.klap.test

import me.sargunvohra.lib.klap.annotation.ListArg

data class TestList(
        @ListArg
        val list: List<String>
) {
    override fun toString() = list.joinToString(", ")
}