package me.sargunvohra.lib.klap.exception

/**
 * Base class for all Klap exceptions
 */
abstract class KlapException(msg: String): Exception(msg)

/**
 * Base class for Klap parsing exceptions.
 */
abstract class KlapArgumentException(msg: String): KlapException(msg)

/**
 * Thrown when an invalid argument is found in the input.
 */
class KlapInvalidArgException(arg: String): KlapArgumentException("invalid argument '$arg'")

/**
 * Thrown when a required key-value pair is not in the input.
 */
class KlapMissingKeyException(key: String): KlapArgumentException("required key '$key' is not specified")

/**
 * Thrown when the class passed as a target to parseArgs doesn't have a usable constructor.
 */
class KlapMissingConstructorException(name: String): KlapException("missing primary constructor on class '$name'")