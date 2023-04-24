package com.github.wordsmemorizer.screens.add_word

import com.github.wordsmemorizer.models.LexicalCategories
import com.github.wordsmemorizer.models.Word
import java.util.*


data class AddWordState(
    val search: String = "",
    val word: Word = Word(name = "")
)
