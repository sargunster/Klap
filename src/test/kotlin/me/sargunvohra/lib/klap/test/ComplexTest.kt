package me.sargunvohra.lib.klap.test

import me.sargunvohra.lib.klap.Klap
import me.sargunvohra.lib.klap.annotation.ListArg
import me.sargunvohra.lib.klap.annotation.SingleArg
import org.testng.annotations.Test
import kotlin.test.assertEquals

class ComplexTest {

    @Test
    fun complex1() = assertEquals(
            ComplexConfig(true, "val", list = listOf("foo", "bar")),
            Klap.parseArgs(listOf("-bx", "val", "foo", "bar"), ComplexConfig::class)
    )

    @Test
    fun complex2() = assertEquals(
            ComplexConfig(true, "world", list = listOf("foo", "bar", "baz")),
            Klap.parseArgs(listOf("-b", "foo", "bar", "baz", "-x", "world"), ComplexConfig::class)
    )

    data class ComplexConfig(
            @SingleArg("b")
            val b: Boolean,

            @SingleArg("x")
            val x: String,

            @SingleArg("y")
            val y: String = "default",

            @ListArg
            val list: List<String>
    )
}