package me.sargunvohra.lib.klap.internal

internal sealed class Arg {
    class FlagArgs(val keys: List<String>): Arg()
    class ValueArgs(val pair: Pair<String, String>, val extraFlags: FlagArgs): Arg()
    class WordArgs(val values: List<String>): Arg()
}