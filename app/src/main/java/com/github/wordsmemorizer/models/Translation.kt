package com.github.wordsmemorizer.models


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Translation(
    val language: String,
    val notes: List<NoteX>,
    val text: String
):Parcelable