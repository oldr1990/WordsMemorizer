package com.github.wordsmemorizer.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.github.wordsmemorizer.models.SimpleWord

@Dao
interface SimpleWordDao {
    @Upsert
    suspend fun upsertWord(word: SimpleWord)

    @Delete
    suspend fun deleteWord(word: SimpleWord)

    @Query("SELECT * FROM simple_word ORDER BY level ASC LIMIT 10")
    suspend fun getWordsSortedByLevel(): List<SimpleWord>

    @Query("SELECT * FROM simple_word WHERE last_time_shown < :time  LIMIT 10")
    suspend fun getWordsOlderThan(time: Long): List<SimpleWord>

    @Query("SELECT * FROM simple_word WHERE last_time_shown < :time AND level > :startLevel AND level < :endLevel LIMIT 10")
    suspend fun getOlderLevel(time: Long, startLevel: Int, endLevel: Int): List<SimpleWord>

    @Query("SELECT * FROM simple_word WHERE word = :word  LIMIT 1")
    suspend fun searchWord(word: String): List<SimpleWord>
    @Query("SELECT * FROM simple_word WHERE id = :id")
    suspend fun getWord(id: Int): List<SimpleWord>
}