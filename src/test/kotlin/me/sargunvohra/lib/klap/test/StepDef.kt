package me.sargunvohra.lib.klap.test

import cucumber.api.java.en.*
import me.sargunvohra.lib.klap.Klap
import me.sargunvohra.lib.klap.exception.KlapInvalidArgException
import me.sargunvohra.lib.klap.exception.KlapMissingKeyException
import kotlin.reflect.KClass
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class StepDef {
    lateinit var target: KClass<*>
    lateinit var input: Array<String>

    @Given("a class with (boolean|string|boolean and string|a list of|all types of) properties")
    fun givenClass(which: String) {
        target = when (which) {
            "boolean" -> TestBools::class
            "string" -> TestStrings::class
            "boolean and string" -> TestBoolsStrings::class
            "a list of" -> TestList::class
            "all types of" -> TestAll::class
            else -> throw IllegalStateException()
        }
    }

    @When("I provide the arguments (.*)")
    fun whenArgs(args: List<String>) {
        input = args.toTypedArray()
    }

    @When("I provide empty arguments")
    fun whenEmptyArgs() = whenArgs(emptyList())

    @Then("it has values (.*)")
    fun thenHasValues(expected: String) {
        val result = Klap.parseArgs(input, target)
        assertEquals(expected, result.toString())
    }

    @Then("it has empty values")
    fun thenHasEmpty() = thenHasValues("")

    @Then("it fails with (an invalid argument|a missing key) exception")
    fun thenFailsWith(expected: String) {
        assertFailsWith(when (expected) {
            "an invalid argument" -> KlapInvalidArgException::class
            "a missing key" -> KlapMissingKeyException::class
            else -> throw IllegalStateException()
        }) {
            Klap.parseArgs(input, target)
        }
    }
}