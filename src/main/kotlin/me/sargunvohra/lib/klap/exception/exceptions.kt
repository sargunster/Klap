package me.sargunvohra.lib.klap.exception

abstract class KlapException(msg: String): Exception(msg)

abstract class KlapArgumentException(msg: String): KlapException(msg)

class KlapInvalidArgException(arg: String): KlapArgumentException("invalid argument '$arg'")

class KlapMissingKeyException(key: String): KlapArgumentException("required key '$key' is not specified")

class MissingConstructorException(name: String): KlapException("missing primary constructor on class '$name'")