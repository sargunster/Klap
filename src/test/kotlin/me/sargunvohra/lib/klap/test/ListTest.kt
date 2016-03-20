package me.sargunvohra.lib.klap.test

import me.sargunvohra.lib.klap.Klap
import me.sargunvohra.lib.klap.annotation.ListArg
import org.testng.annotations.Test
import kotlin.test.assertEquals

class ListTest {

    @Test
    fun listEmpty() = assertEquals(
            ListConfig(emptyList()),
            Klap.parseArgs(emptyList(), ListConfig::class)
    )

    @Test
    fun listArgs() = assertEquals(
            ListConfig(listOf("foo", "bar", "baz", "qux")),
            Klap.parseArgs(listOf("foo", "bar", "baz", "qux"), ListConfig::class)
    )

    data class ListConfig(
            @ListArg
            val list: List<String>
    )
}