package com.github.wordsmemorizer.network.oxford

import com.github.wordsmemorizer.models.response.OxfordResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface OxfordApi {
    @GET("/api/v2/entries/en-us/{word}")
    suspend fun searchWord(
        @Path("word") word: String,
        @Query("strictMatch") strictMatch: Boolean = false
    ): OxfordResponse
}