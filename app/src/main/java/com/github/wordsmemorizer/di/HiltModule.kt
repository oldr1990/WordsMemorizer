package com.github.wordsmemorizer.di

import android.content.Context
import androidx.room.Room
import com.github.wordsmemorizer.room.MainRoomDatabase
import com.github.wordsmemorizer.room.FlashcardDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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
    fun providesSimpleWordDao(database: MainRoomDatabase): FlashcardDao = database.questionDao()


}