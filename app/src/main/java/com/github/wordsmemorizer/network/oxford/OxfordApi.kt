package com.github.wordsmemorizer.network.oxford

import com.github.wordsmemorizer.models.response.TranslationResponse
import com.github.wordsmemorizer.network.oxfordApiKey
import com.github.wordsmemorizer.network.oxfordAppId
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface OxfordApi {
    @GET("/api/v2/entries/en-us/{word}")
    suspend fun searchWord(
        @Path("word") word: String,
        @Query("strictMatch") strictMatch: Boolean = false
    ): TranslationResponse
}