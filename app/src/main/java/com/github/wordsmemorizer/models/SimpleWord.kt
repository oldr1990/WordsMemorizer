package com.github.wordsmemorizer.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "simple_word")
data class SimpleWord(
    @PrimaryKey(autoGenerate = true)  val id: Int = 0,
    val word: String = "",
    val answer: String = "",
    val hint: String = "",
    @ColumnInfo(name = "last_time_shown")  val lastTimeShowed: Long = Date().time,
    val level: Int = 0,
)
