package com.github.wordsmemorizer.models.response


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteX(
    val text: String,
    val type: String
):Parcelable