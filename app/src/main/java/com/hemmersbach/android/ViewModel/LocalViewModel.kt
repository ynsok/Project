package com.hemmersbach.android.ViewModel

import android.arch.lifecycle.ViewModel
import com.hemmersbach.android.Jokes
import com.hemmersbach.android.JokesRepository.JokesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LocalViewModel(private val repository: JokesRepository) : ViewModel() {
    private val job = Job()
    private val coroutineContext: CoroutineContext get() = job + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    fun getAllJokes() = repository.getAllJokes()
    fun removeFromDatabase(joke: Jokes) = scope.launch { repository.removeFromDatabase(joke) }

    override fun onCleared() {
        super.onCleared()
        cancelNetworkRequest()
    }

    private fun cancelNetworkRequest() = job.cancel()
}