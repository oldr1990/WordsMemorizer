package com.github.wordsmemorizer.models.response


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class DatasetCrossLink(
    @Json(name = "entry_id")
    val entryId: String,
    val language: String,
    @Json(name = "sense_id")
    val senseId: String
):Parcelable