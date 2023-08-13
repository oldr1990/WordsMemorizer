package com.github.wordsmemorizer.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.github.wordsmemorizer.models.Flashcard

@Dao
interface FlashcardDao {
    @Upsert
    suspend fun upsertFlashcard(card: Flashcard)

    @Delete
    suspend fun deleteFlashcard(card: Flashcard)

    @Query("SELECT * FROM flashcards ORDER BY level ASC LIMIT 10")
    suspend fun getFlashcardsSortedByLevel(): List<Flashcard>

    @Query("SELECT * FROM flashcards WHERE last_time_showed < :time  LIMIT 10")
    suspend fun getFlashcardsOlderThan(time: Long): List<Flashcard>

    @Query("SELECT * FROM flashcards WHERE last_time_showed < :time AND level > :startLevel AND level < :endLevel LIMIT 10")
    suspend fun getOlderLevel(time: Long, startLevel: Int, endLevel: Int): List<Flashcard>

    @Query("SELECT * FROM flashcards WHERE id = :id  LIMIT 1")
    suspend fun searchFlashcard(id: Int): List<Flashcard>
}