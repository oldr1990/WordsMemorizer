package com.github.wordsmemorizer.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.wordsmemorizer.models.Flashcard

@Database(
    entities = [Flashcard::class],
    version = 4
)
@TypeConverters(ListToStringConverter::class)
abstract class MainRoomDatabase : RoomDatabase() {
    abstract fun questionDao(): FlashcardDao
}