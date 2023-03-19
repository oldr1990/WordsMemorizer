package com.github.wordsmemorizer.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val sound: String,
    val phonetic: String,
    val level: Int,
    private val definitions: ArrayList<String>,
    private val lexicalTypes: ArrayList<String>,
    val lastTimeShown: Long,
)