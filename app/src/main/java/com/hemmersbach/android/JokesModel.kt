package com.hemmersbach.android

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

data class JokesModel(
    val type: String,
    val value: List<Value>
)

data class Value(
    val categories: List<String>,
    val id: Int,
    val joke: String,
    var rating: Float = 0.0F
)

@Entity(tableName = "jokesTable")
data class Jokes(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val categories: String = "",
    val joke: String,
    var rating: Float = 0.0F
)