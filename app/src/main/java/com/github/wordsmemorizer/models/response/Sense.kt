package com.github.wordsmemorizer.models.response


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Sense(
    val shortDefinitions: List<String>?,
):Parcelable