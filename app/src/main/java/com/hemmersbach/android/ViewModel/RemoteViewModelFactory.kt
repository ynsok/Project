package com.hemmersbach.android.ViewModel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.hemmersbach.android.JokesRepository.JokesRepository

class RemoteViewModelFactory(private val repository: JokesRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RemoteViewModel(repository) as T
    }
}