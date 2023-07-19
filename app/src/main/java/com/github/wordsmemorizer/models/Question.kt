package com.github.wordsmemorizer.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey(autoGenerate = true)  val id: Int = 0,
    val answer: String = "",
    val question: String = "",
    val hint: String = "",
    @ColumnInfo(name = "last_time_showed")  val lastTimeShowed: Long = Date().time,
    val level: Int = 0,
){
    val isValid: Boolean get() = answer.isNotEmpty() && question.isNotEmpty()
}
