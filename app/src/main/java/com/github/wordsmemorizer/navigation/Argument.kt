package com.github.wordsmemorizer.navigation

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.Exception

interface Argument<T> {

    val adapter: JsonAdapter<T> get() = moshi.adapter(this.javaClass as Class<T>)

    val moshi: Moshi get() = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    fun toJson(): String = adapter.toJson(this as T).orEmpty()

    fun fromJson(string: String): T? = try {
        adapter.fromJson(string)
    } catch (e: Exception) {
        null
    }
}

class ArgumentString(val value: String) : Argument<ArgumentString>

class ArgumentInt(val value: Int) : Argument<ArgumentInt>