package com.github.wordsmemorizer.models.response


import android.os.Parcelable
import com.github.wordsmemorizer.models.Word
import kotlinx.parcelize.Parcelize

@Parcelize
data class OxfordResponse(
    val results: List<OxfordResult>,
    val word: String
) : Parcelable {
    val getWord: Word
        get() {
            var sound = ""
            var phonetic = ""
            val lexicalCategory = mutableSetOf<String>()
            val definitions = mutableSetOf<String>()
            results.first().lexicalEntries.forEach {
                it.entries?.forEach { entry ->
                    entry.pronunciations?.forEach { pronunciation ->
                        if (sound.isEmpty()) {
                            sound = pronunciation.audioFile.orEmpty()
                        }
                        if (phonetic.isEmpty()) {
                            phonetic = pronunciation.phoneticSpelling.orEmpty()
                        }
                    }
                    entry.senses?.forEach { sense ->
                        sense.shortDefinitions?.forEach { define ->
                            if (define.isNotEmpty()) {
                                definitions.add(define)
                            }
                        }
                    }
                }
                it.lexicalCategory.text?.let { type ->
                    if (type.isNotEmpty()) {
                        lexicalCategory.add(type)
                    }
                }

            }
            return Word(
                name = word,
                sound = sound,
                phonetic = phonetic,
                lexicalTypes = lexicalCategory.toList(),
                definitions = definitions.toList()
            )
        }
}