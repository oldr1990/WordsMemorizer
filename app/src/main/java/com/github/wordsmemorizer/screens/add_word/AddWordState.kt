package com.github.wordsmemorizer.screens.add_word

import com.github.wordsmemorizer.models.LexicalCategories


data class AddWordState(
    val name: String = "",
    val sound: String = "",
    val phonetic: String = "",
    val definitions: List<String> = emptyList(),
    val lexicalTypes: List<LexicalCategories> = emptyList(),
)
