package me.sargunvohra.lib.klap.annotation

/**
 * The field annotated with this will get the additional arguments not part of a flag or key-value pair.
 */
annotation class ListArg

/**
 * The field annotated with this will be either a boolean flag or a string key-value pair, depending on type.
 */
annotation class SingleArg(vararg val names: String)