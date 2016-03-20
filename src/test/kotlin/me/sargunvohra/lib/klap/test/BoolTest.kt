package me.sargunvohra.lib.klap.test

import me.sargunvohra.lib.klap.Klap
import me.sargunvohra.lib.klap.annotation.SingleArg
import me.sargunvohra.lib.klap.exception.KlapInvalidArgException
import org.testng.annotations.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class BoolTest {

    @Test
    fun boolEmpty() = assertEquals(
            BoolConfig(false, false, false, false, false, false),
            Klap.parseArgs(emptyList(), BoolConfig::class)
    )

    @Test
    fun boolSingle() = assertEquals(
            BoolConfig(true, true, true, false, false, false),
            Klap.parseArgs(listOf("-a", "-bc", "-abc"), BoolConfig::class)
    )

    @Test
    fun boolWord() = assertEquals(
            BoolConfig(false, false, false, false, true, false),
            Klap.parseArgs(listOf("--hello"), BoolConfig::class)
    )

    @Test
    fun boolSecondary() = assertEquals(
            BoolConfig(false, false, false, false, true, true),
            Klap.parseArgs(listOf("-hw"), BoolConfig::class)
    )

    @Test
    fun boolBoth() = assertEquals(
            BoolConfig(false, true, true, true, false, true),
            Klap.parseArgs(listOf("--world", "-bc", "-d"), BoolConfig::class)
    )

    @Test
    fun boolExtra() = assertFailsWith<KlapInvalidArgException> {
        Klap.parseArgs(listOf("--wrong"), BoolConfig::class)
    }

    data class BoolConfig(
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
    )
}

