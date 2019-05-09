package com.hemmersbach.android.UI

import com.hemmersbach.android.JokesRepository.JokesRepository
import com.hemmersbach.android.ViewModel.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideViewModel(repository: JokesRepository) = MainViewModel(repository)
}