package com.github.wordsmemorizer.utils

import org.json.JSONObject
import retrofit2.HttpException

fun parseError(e: HttpException): String {
    val json = e.response()?.errorBody()?.string()?.replace("\n", "")
    return JSONObject(json.orEmpty()).getString("error").orEmpty()
}