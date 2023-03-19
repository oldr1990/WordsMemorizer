package com.github.wordsmemorizer.models.response


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Sense(
    val datasetCrossLinks: List<DatasetCrossLink>,
    val examples: List<Example>,
    val id: String,
    val notes: List<NoteX>,
    val registers: List<Register>,
    val translations: List<TranslationX>
):Parcelable