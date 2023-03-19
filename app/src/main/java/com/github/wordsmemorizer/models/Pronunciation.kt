package com.github.wordsmemorizer.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Pronunciation(
    val audioFile: String,
    val dialects: List<String>,
    val phoneticNotation: String,
    val phoneticSpelling: String
):Parcelable