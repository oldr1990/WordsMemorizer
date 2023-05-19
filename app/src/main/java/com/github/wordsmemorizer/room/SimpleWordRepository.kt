package com.github.wordsmemorizer.room

import com.github.wordsmemorizer.models.SimpleWord
import javax.inject.Inject

class SimpleWordRepository @Inject constructor(val dao: SimpleWordDao) {

   suspend fun addWord(word: SimpleWord){
        dao.upsertWord(word)
    }

   suspend fun deleteWord(word: SimpleWord){
        dao.deleteWord(word)
    }

    suspend fun getWordsShowed(time: Long): List<SimpleWord> {
        return dao.getWordsOlderThan(time)
    }

    suspend fun getWordsByLevel(): List<SimpleWord> {
        return dao.getWordsSortedByLevel()
    }

    suspend fun wordNotExist(word: String): Boolean {
        return  dao.searchWord(word).isEmpty()
    }

}