package com.github.wordsmemorizer.models.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Pronunciation(
    val audioFile: String?,
    val phoneticSpelling: String?
):Parcelable