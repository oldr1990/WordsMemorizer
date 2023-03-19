package com.github.wordsmemorizer.models


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TranslationX(
    val language: String,
    val text: String
):Parcelable