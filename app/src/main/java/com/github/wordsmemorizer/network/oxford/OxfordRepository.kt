package com.github.wordsmemorizer.network.oxford

import com.github.wordsmemorizer.models.response.OxfordResponse
import com.github.wordsmemorizer.network.Response
import javax.inject.Inject

class OxfordRepository @Inject constructor(private val api: OxfordApi) {
    suspend fun searchWord(search: String): Response<OxfordResponse> =
        try {
            Response.Success(api.searchWord(search))
        } catch (e: Exception) {
            Response.Error(e)
        }
}