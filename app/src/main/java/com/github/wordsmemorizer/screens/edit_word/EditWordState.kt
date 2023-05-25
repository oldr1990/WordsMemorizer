package com.github.wordsmemorizer.screens.edit_word

import com.github.wordsmemorizer.models.SimpleWord

data class EditWordState(
    val word: SimpleWord = SimpleWord(),
    val showFieldError: Boolean = false
)
