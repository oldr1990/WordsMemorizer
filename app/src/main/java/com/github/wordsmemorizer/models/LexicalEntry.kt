package com.github.wordsmemorizer.models


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LexicalEntry(
    val entries: List<Entry>,
    val language: String,
    val lexicalCategory: LexicalCategory,
    val text: String
):Parcelable