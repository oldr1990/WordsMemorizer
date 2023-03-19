package com.github.wordsmemorizer.models.response


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Entry(
    val pronunciations: List<Pronunciation>,
    val senses: List<Sense>
):Parcelable