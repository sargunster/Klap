package me.sargunvohra.lib.klap.exception

/**
 * TODO
 */
abstract class KlapException(msg: String): Exception(msg)

/**
 * TODO
 */
abstract class KlapArgumentException(msg: String): KlapException(msg)

/**
 * TODO
 */
class KlapInvalidArgException(arg: String): KlapArgumentException("invalid argument '$arg'")

/**
 * TODO
 */
class KlapMissingKeyException(key: String): KlapArgumentException("required key '$key' is not specified")

/**
 * TODO
 */
class MissingConstructorException(name: String): KlapException("missing primary constructor on class '$name'")