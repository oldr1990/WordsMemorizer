package com.github.wordsmemorizer.room

import com.github.wordsmemorizer.models.Question
import javax.inject.Inject

class QuestionRepository @Inject constructor(val dao: QuestionDao) {

   suspend fun addQuestion(question: Question){
        dao.upsertQuestion(question)
    }

   suspend fun deleteQuestion(question: Question){
        dao.deleteQuestion(question)
    }

    suspend fun getQuestionOlderThan(time: Long): List<Question> {
        return dao.getQuestionOlderThan(time)
    }

    suspend fun questionsByLevel(): List<Question> {
        return dao.getQustionsSortedByLevel()
    }

    suspend fun doesQuestionExist(answer: String): Boolean {
        return  dao.searchAnswer(answer).isEmpty()
    }

}