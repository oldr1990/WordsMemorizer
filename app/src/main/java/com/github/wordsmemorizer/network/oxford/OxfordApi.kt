package com.github.wordsmemorizer.network.oxford

import com.github.wordsmemorizer.models.response.TranslationResponse
import com.github.wordsmemorizer.network.oxfordApi
import com.github.wordsmemorizer.network.oxfordAppKey
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface OxfordApi {
    @Headers(
        "Accept: application/json",
        "app_id: $oxfordAppKey",
        "app_key: $oxfordApi"
    )
    @GET("/api/v2/entries/en-us/")
    suspend fun searchWord(
        word: String,
        @Query("strictMatch") strictMatch: Boolean = false
    ): TranslationResponse
}