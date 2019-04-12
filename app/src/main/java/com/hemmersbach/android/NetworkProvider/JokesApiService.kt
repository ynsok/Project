package com.hemmersbach.android.NetworkProvider

import com.hemmersbach.android.JokesModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface JokesApiService {
    @GET("{count}/?escape=javascript")
    fun getRandomCountOfJokesAsync(@Path("count") count: Int): Deferred<Response<JokesModel>>

    companion object {
        const val BASE_URL = "http://api.icndb.com/jokes/random/"
    }
}