package com.hemmersbach.android.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.hemmersbach.android.Jokes

@Database(entities = [Jokes::class], version = 1, exportSchema = false)
abstract class JokesDatabase : RoomDatabase() {
    abstract fun jokesDao(): JokesDao
}