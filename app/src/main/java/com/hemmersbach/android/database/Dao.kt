package com.hemmersbach.android.database

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Dao
import com.hemmersbach.android.Jokes

@Dao
interface Dao {
    @Query("Select * From jokesTable Order by rating asc")
    fun getAllJokesAscByRating(): LiveData<MutableList<Jokes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addJoke(Joke: Jokes)

    @Delete
    fun removeJoke(joke: Jokes)
}