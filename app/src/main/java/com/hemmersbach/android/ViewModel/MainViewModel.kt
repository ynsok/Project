package com.hemmersbach.android.ViewModel

import android.arch.lifecycle.ViewModel
import android.util.Log
import com.hemmersbach.android.JokesRepository.JokesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(private val repository: JokesRepository) : ViewModel() {

    init {
        Log.i("AppfMainViewModel", this.toString())
    }
}