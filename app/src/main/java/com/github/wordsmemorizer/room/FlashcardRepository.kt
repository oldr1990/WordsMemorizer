package com.github.wordsmemorizer.room

import com.github.wordsmemorizer.models.Flashcard
import javax.inject.Inject

class FlashcardRepository @Inject constructor(val dao: FlashcardDao) {

   suspend fun addFlashcard(flashcard: Flashcard){
        dao.upsertFlashcard(flashcard)
    }

   suspend fun deleteFlashcard(flashcard: Flashcard){
        dao.deleteFlashcard(flashcard)
    }

    suspend fun getFlashcardOlderThan(time: Long): List<Flashcard> {
        return dao.getFlashcardsOlderThan(time)
    }

    suspend fun flashcardsByLevel(): List<Flashcard> {
        return dao.getFlashcardsSortedByLevel()
    }

    suspend fun getFlashcard(id: Int): Flashcard? {
        val list = dao.searchFlashcard(id)
        return if ( list.isNotEmpty()){
            list.first()
        } else {
            null
        }
    }

}