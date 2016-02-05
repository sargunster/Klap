package me.sargunvohra.lib.klap.test

data class TestBoolsStrings(
        val one: Boolean,
        val two: Boolean,
        val three: String,
        val four: String = "four"
) {
    override fun toString() = listOf(one, two, three, four).joinToString(", ")
}