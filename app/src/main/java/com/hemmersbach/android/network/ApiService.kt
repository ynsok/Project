package com.hemmersbach.android.network

import com.hemmersbach.android.JokesModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("{count}/?escape=javascript")
    fun getRandomCountOfJokesAsync(@Path("count") count: Int): Deferred<Response<JokesModel>>
}