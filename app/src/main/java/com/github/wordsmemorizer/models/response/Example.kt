package com.github.wordsmemorizer.models.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Example(
    val text: String,
    val translations: List<Translation>
): Parcelable