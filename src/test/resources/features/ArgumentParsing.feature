Feature: Command line argument parsing
  Klap should be able to parse command line arguments and instantiate a Kotlin data class with the correct values.

#  Flags are true if not in the arguments, or false otherwise.

  Scenario: No arguments
    Given a class with boolean properties
    When I provide empty arguments
    Then it has values false, false, false, false, false, false

#  Flags can be specified by a single dash followed by single-letter flags, or a double dash followed by longer flags.

  Scenario: Single dash denotes single letter flags
    Given a class with boolean properties
    When I provide the arguments -a, -bc, -abc
    Then it has values true, true, true, false, false, false

  Scenario: Double dash denotes word flags
    Given a class with boolean properties
    When I provide the arguments --hello
    Then it has values false, false, false, false, true, false

  Scenario: Both single and double dash
    Given a class with boolean properties
    When I provide the arguments --world, -bc, -d
    Then it has values false, true, true, true, false, true

#  If flag is specified that can't be handled, Klap throws an exception.

  Scenario: Extra argument
    Given a class with boolean properties
    When I provide the arguments --wrong
    Then it fails with an invalid argument exception

#  Key-value arguments can be specified by a flag followed by a regular argument

  Scenario: Only required properties
    Given a class with string properties
    When I provide the arguments -a, test1, --hello, test2
    Then it has values test1, b, test2, world

  Scenario: All properties
    Given a class with string properties
    When I provide the arguments -a, test1, --hello, test2, -b, test3, --world, test4
    Then it has values test1, test3, test2, test4

  Scenario: Missing key
    Given a class with string properties
    When I provide the arguments -a, test1
    Then it fails with a missing key exception

#  List arguments are any arguments that do not start with a "-" and are not the value in a key-value pair.

  Scenario: Empty list
    Given a class with a list of properties
    When I provide empty arguments
    Then it has empty values

  Scenario: Non-empty list
    Given a class with a list of properties
    When I provide the arguments foo, bar, baz, qux
    Then it has values foo, bar, baz, qux

#  TODO

  Scenario:
    Given a class with boolean and string properties

#  TODO

  Scenario:
    Given a class with all types of properties