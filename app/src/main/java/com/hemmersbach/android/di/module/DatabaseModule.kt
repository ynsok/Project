package com.hemmersbach.android.di.module

import android.app.Application
import androidx.room.Room
import com.hemmersbach.android.database.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDb(application: Application) =
        Room.databaseBuilder(application, Database::class.java, "joke.db").build()

    @Provides
    @Singleton
    fun provideDao(database: Database) = database.jokesDao()
}