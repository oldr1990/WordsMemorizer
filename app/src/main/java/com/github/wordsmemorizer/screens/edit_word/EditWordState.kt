package com.github.wordsmemorizer.screens.edit_word

import com.github.wordsmemorizer.models.Question

data class EditWordState(
    val word: Question = Question(),
    val showFieldError: Boolean = false
)
