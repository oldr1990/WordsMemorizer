package com.github.wordsmemorizer.models.response


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OxfordResponse(
    val id: String,
    val metadata: Metadata,
    val results: List<OxfordResult>,
    val word: String
):Parcelable