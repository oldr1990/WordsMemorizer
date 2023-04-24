package com.github.wordsmemorizer.network.oxford

import com.github.wordsmemorizer.models.Word
import com.github.wordsmemorizer.models.response.OxfordResponse
import com.github.wordsmemorizer.network.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OxfordRepository @Inject constructor(private val api: OxfordApi) {
    suspend fun searchWord(search: String): Response<Word> =
        try {
            val result = api.searchWord(search).getWord
            Response.Success(result)
        } catch (e: Exception) {
            Response.Error(e)
        }

}