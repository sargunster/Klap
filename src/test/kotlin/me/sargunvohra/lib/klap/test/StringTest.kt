package me.sargunvohra.lib.klap.test

import me.sargunvohra.lib.klap.Klap
import me.sargunvohra.lib.klap.annotation.SingleArg
import me.sargunvohra.lib.klap.exception.KlapMissingKeyException
import org.testng.annotations.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class StringTest {

    @Test
    fun stringRequired() = assertEquals(
            StringConfig(a = "test1", hello = "test2"),
            Klap.parseArgs(listOf("-a", "test1", "--hello", "test2"), StringConfig::class)
    )

    @Test
    fun stringAll() = assertEquals(
            StringConfig(a = "test1", hello = "test2", b = "test3", world = "test4"),
            Klap.parseArgs(listOf("-a", "test1", "--hello", "test2", "-b", "test3", "--world", "test4"), StringConfig::class)
    )

    @Test
    fun stringSecondary() = assertEquals(
            StringConfig(a = "test1", hello = "test2", b = "test3", world = "test4"),
            Klap.parseArgs(listOf("-a", "test1", "-h", "test2", "-b", "test3", "-w", "test4"), StringConfig::class)
    )

    @Test
    fun stringMissing() = assertFailsWith<KlapMissingKeyException> {
        Klap.parseArgs(listOf("-a", "test1"), StringConfig::class)
    }

    data class StringConfig(
            @SingleArg("a")
            val a: String,

            @SingleArg("b")
            val b: String = "b",

            @SingleArg("hello", "h")
            val hello: String,

            @SingleArg("world", "w")
            val world: String = "world"
    )
}