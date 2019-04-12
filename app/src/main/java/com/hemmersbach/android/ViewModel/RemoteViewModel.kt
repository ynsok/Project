package com.hemmersbach.android.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.hemmersbach.android.Jokes

import com.hemmersbach.android.JokesRepository.JokesRepository
import com.hemmersbach.android.Result.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RemoteViewModel(private val repository: JokesRepository) : ViewModel() {

    private val _jokes = MediatorLiveData<MutableList<Jokes>>()
    private val _error = MediatorLiveData<String>()
    private val job = Job()
    private val coroutineContext: CoroutineContext get() = job + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    val joke: LiveData<MutableList<Jokes>> get() = _jokes
    val error: LiveData<String> get() = _error

    fun getJokes() {
        scope.launch {
            val jokesRespond = repository.getJokes()
            when (jokesRespond) {
                is Result.Success -> _jokes.postValue(jokesRespond.data)
                is Result.Failure -> _error.postValue(jokesRespond.exception.message)
            }
        }
    }

    fun addJoke(value: Jokes) = scope.launch { repository.addJoke(value) }

    override fun onCleared() {
        super.onCleared()
        cancelNetworkRequest()
    }

    private fun cancelNetworkRequest() = job.cancel()
}