package com.github.wordsmemorizer.models.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DatasetCrossLink(
    @SerializedName("entry_id")
    val entryId: String,
    val language: String,
    @SerializedName("sense_id")
    val senseId: String
):Parcelable