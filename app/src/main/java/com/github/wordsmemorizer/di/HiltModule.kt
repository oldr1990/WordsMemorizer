package com.github.wordsmemorizer.di

import android.content.Context
import androidx.room.Room
import com.github.wordsmemorizer.network.oxford.OxfordApi
import com.github.wordsmemorizer.network.oxfordApiKey
import com.github.wordsmemorizer.network.oxfordAppId
import com.github.wordsmemorizer.network.oxfordBaseUrl
import com.github.wordsmemorizer.room.MainRoomDatabase
import com.github.wordsmemorizer.room.SimpleWordDao
import com.github.wordsmemorizer.room.WordDao
import com.github.wordsmemorizer.utils.isConnected
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toJavaDuration

@InstallIn(SingletonComponent::class)
@Module
object HiltModule {
    @Provides
    @Singleton
    fun providesRoomDB(@ApplicationContext context: Context): MainRoomDatabase =
        Room.databaseBuilder(context, MainRoomDatabase::class.java, "word.memorizer")
            .build()

    @Provides
    @Singleton
    fun providesWordDao(database: MainRoomDatabase): WordDao = database.wordDao()
 @Provides
    @Singleton
    fun providesSimpleWordDao(database: MainRoomDatabase): SimpleWordDao = database.simpleWordDao()

    @Provides
    fun providesHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addNetworkInterceptor(Interceptor {
                val request = it.request()
                val newRequest = request.newBuilder()
                    .header("Accept", "application/json")
                    .header("app_id", oxfordAppId)
                    .header("app_key", oxfordApiKey)
                    .method(request.method, request.body)
                    .build()
                it.proceed(newRequest)
            })
            .callTimeout(10.seconds.toJavaDuration())
            .connectTimeout(3.seconds.toJavaDuration())
            .build()
        return client
    }

    @Provides
    @Singleton
    fun providesOxfordApi(client: OkHttpClient): OxfordApi =
        Retrofit.Builder()
            .baseUrl(oxfordBaseUrl)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
}