package com.hemmersbach.android.JokesRepository

import android.util.Log
import com.hemmersbach.android.Convert.ConvertDataType
import com.hemmersbach.android.Database.JokesDao
import com.hemmersbach.android.Extention.ApiCall
import com.hemmersbach.android.Jokes
import com.hemmersbach.android.NetworkProvider.JokesApiService
import com.hemmersbach.android.Result.Result
import com.hemmersbach.android.SharedPreferences.SharedPreferences
import com.hemmersbach.android.Util.ConnectionManager
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JokesRepository
@Inject
constructor(
    private val jokesApiService: JokesApiService,
    private val connectionManager: ConnectionManager,
    private val sharedPreferences: SharedPreferences,
    private val jokesDao: JokesDao
) {
    private fun checkConnection() = connectionManager.checkIfIsInternet()

    suspend fun getJokes() =
        ApiCall(call = { remoteJokes(getCountOfItem()) }, errorMessage = "Error occurred")

    private suspend fun remoteJokes(count: Int): Result<MutableList<Jokes>> {
        val response = jokesApiService.getRandomCountOfJokesAsync(count).await()
        if (response.isSuccessful) {
            val newList = ConvertDataType.convertJokesType(response.body()!!)
            return Result.Success(newList)
        }
        return Result.Failure(IOException("Error during fetching jokes"))
    }

    private fun getCountOfItem() = sharedPreferences.getItemCountValue()

    fun getAllJokes() = jokesDao.getAllJokesAscByRating()
    fun addJoke(Jokes: Jokes) = jokesDao.addJoke(Jokes)
    fun removeFromDatabase(jokes: Jokes) = jokesDao.removeJoke(jokes)

    init {
        Log.i("AppfRepository", this.toString())
    }
}