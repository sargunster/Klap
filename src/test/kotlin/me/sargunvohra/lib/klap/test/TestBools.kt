package me.sargunvohra.lib.klap.test

data class TestBools(
        val a: Boolean,
        val b: Boolean,
        val c: Boolean,
        val d: Boolean,
        val hello: Boolean,
        val world: Boolean
) {
    override fun toString() = listOf(a, b, c, d, hello, world).joinToString(", ")
}