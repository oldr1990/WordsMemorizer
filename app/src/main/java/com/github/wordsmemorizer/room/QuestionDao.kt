package com.github.wordsmemorizer.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.github.wordsmemorizer.models.Question

@Dao
interface QuestionDao {
    @Upsert
    suspend fun upsertQuestion(word: Question)

    @Delete
    suspend fun deleteQuestion(word: Question)

    @Query("SELECT * FROM questions ORDER BY level ASC LIMIT 10")
    suspend fun getQustionsSortedByLevel(): List<Question>

    @Query("SELECT * FROM questions WHERE last_time_showed < :time  LIMIT 10")
    suspend fun getQuestionOlderThan(time: Long): List<Question>

    @Query("SELECT * FROM questions WHERE last_time_showed < :time AND level > :startLevel AND level < :endLevel LIMIT 10")
    suspend fun getOlderLevel(time: Long, startLevel: Int, endLevel: Int): List<Question>

    @Query("SELECT * FROM questions WHERE answer = :answer  LIMIT 1")
    suspend fun searchAnswer(answer: String): List<Question>
    @Query("SELECT * FROM questions WHERE id = :id")
    suspend fun getWord(id: Int): List<Question>
}