package com.github.wordsmemorizer.screens.add_word


data class AddWordState(
    val name: String = "",
    val sound: String = "",
    val phonetic: String = "",
    val definitions: List<String> = emptyList(),
    val lexicalTypes: List<String> = emptyList(),
)
