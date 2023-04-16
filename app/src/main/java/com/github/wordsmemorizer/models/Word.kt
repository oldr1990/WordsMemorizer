package com.github.wordsmemorizer.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "words")
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val translation: String= "",
    val sound: String = "",
    val phonetic: String = "",
    val level: Int = 0,
    val definitions: List<String> = emptyList(),
    @ColumnInfo(name = "lexical_types")
    val lexicalTypes: List<String> = emptyList(),
    @ColumnInfo(name = "last_time_shown")
    val lastTimeShown: Long = Date().time,
)