package com.github.wordsmemorizer.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.github.wordsmemorizer.models.Word

@Dao
interface WordDao {

    @Upsert
    suspend fun upsertWord(word: Word)

    @Delete
    suspend fun deleteWord(word: Word)

    @Query("SELECT * FROM word ORDER BY level ASC LIMIT 10")
    fun getWordsSortedByLevel(): List<Word>

    @Query("SELECT * FROM word ORDER last_time_shown < :time")
    fun getWordsSortedByDate(time: Long): List<Word>
}