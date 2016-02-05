package me.sargunvohra.lib.klap.test

data class TestStrings(
        val a: String,
        val b: String = "b",
        val hello: String,
        val world: String = "world"
) {
    override fun toString() = listOf(a, b, hello, world).joinToString(", ")
}