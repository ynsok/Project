package com.hemmersbach.android.viewModel

import androidx.lifecycle.ViewModel
import com.hemmersbach.android.repository.Repository

class MainViewModel(private val repository: Repository) : ViewModel()
/*
private val jokes: MutableLiveData<Result<List<Joke>>> by lazy {
    MutableLiveData<Result<List<Joke>>>().also {
        loadJokes()
    }
}

fun getJokes(): LiveData<Result<List<Joke>>> {
    return jokes
}

private fun loadJokes() = viewModelScope.launch {
    //1. set loading state
    jokes.value = Result.Loading()

    //2. load jokes
    val response = repository.getJokes()

    //3. notify jokes are loaded
    withContext(Dispatchers.Main) {
        jokes.value = response
    }
}
*/
