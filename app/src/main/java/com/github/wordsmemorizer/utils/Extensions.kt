package com.github.wordsmemorizer.utils

val specialCharacterRegex by lazy { Regex("([\\r\\n\\t&])") }

fun String.removeSpecialCharacters(): String = this.replace(specialCharacterRegex, "")