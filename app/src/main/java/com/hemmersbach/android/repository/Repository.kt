package com.hemmersbach.android.repository

import com.hemmersbach.android.database.Dao
import com.hemmersbach.android.network.ApiService

class Repository(
    private val apiService: ApiService,
    private val dao: Dao
)