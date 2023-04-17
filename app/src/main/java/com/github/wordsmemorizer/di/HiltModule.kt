package com.github.wordsmemorizer.di

import android.content.Context
import androidx.room.Room
import com.github.wordsmemorizer.network.oxford.OxfordApi
import com.github.wordsmemorizer.network.oxfordUrl
import com.github.wordsmemorizer.room.MainRoomDatabase
import com.github.wordsmemorizer.room.RoomRepository
import com.github.wordsmemorizer.room.WordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

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
    fun providesDao(database: MainRoomDatabase): WordDao = database.wordDao()

    @Provides
    @Singleton
    fun providesOxfordApi(): OxfordApi = Retrofit.Builder().baseUrl(oxfordUrl).build().create()


}