package com.github.wordsmemorizer.room

import com.github.wordsmemorizer.models.Word
import javax.inject.Inject

class RoomRepository @Inject constructor(private val dao: WordDao) {
    suspend fun addWordToLibrary(word: Word){
        dao.upsertWord(word)
    }
    suspend fun deleteWordFromLibrary(word: Word){
        dao.deleteWord(word)
    }
}