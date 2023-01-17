package com.github.wordsmemorizer.models


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LexicalCategory(
    val id: String,
    val text: String
):Parcelable