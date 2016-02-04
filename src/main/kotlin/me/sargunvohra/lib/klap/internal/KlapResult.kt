package me.sargunvohra.lib.klap.internal

internal data class KlapResult(
        val flagArgs: List<String> = emptyList(),
        val valueArgs: Map<String, String> = emptyMap(),
        val wordArgs: List<String> = emptyList()
)