package com.github.wordsmemorizer.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Result(
    val id: String,
    val language: String,
    val lexicalEntries: List<LexicalEntry>,
    val type: String,
    val word: String
):Parcelable