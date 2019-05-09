package com.hemmersbach.android.ViewModel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.hemmersbach.android.JokesRepository.JokesRepository
import java.lang.IllegalArgumentException
import javax.inject.Singleton
import javax.inject.Inject as Inject

@Singleton
class ViewModelFactory
@Inject constructor(private val repository: JokesRepository) :
    ViewModelProvider.NewInstanceFactory() {
    init {
        Log.i("AppfViewModelFactory", this.toString())
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RemoteViewModel::class.java) -> RemoteViewModel(repository) as T
            modelClass.isAssignableFrom(LocalViewModel::class.java) -> LocalViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}