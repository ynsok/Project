package com.hemmersbach.android.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hemmersbach.android.Jokes

@Database(entities = [Jokes::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun jokesDao(): Dao
}