package com.github.wordsmemorizer.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.wordsmemorizer.models.Word

@Database(
    entities = [Word::class],
    version = 2
)
abstract class MainRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}