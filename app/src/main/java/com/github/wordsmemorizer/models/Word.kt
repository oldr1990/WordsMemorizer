package com.github.wordsmemorizer.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val sound: String,
    val phonetic: String,
    val level: Int,
    val definitions: ArrayList<String>,
    @ColumnInfo(name = "lexical_types")
    val lexicalTypes: ArrayList<String>,
    @ColumnInfo(name = "last_time_shown")
    val lastTimeShown: Long,
)