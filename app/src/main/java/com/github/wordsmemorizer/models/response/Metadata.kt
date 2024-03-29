package com.github.wordsmemorizer.models.response


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Metadata(
    val operation: String,
    val provider: String,
    val schema: String
):Parcelable