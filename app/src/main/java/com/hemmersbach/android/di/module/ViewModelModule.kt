package com.hemmersbach.android.di.module

import androidx.lifecycle.ViewModel
import com.hemmersbach.android.di.ViewModelKey
import com.hemmersbach.android.repository.Repository
import com.hemmersbach.android.viewModel.LocalViewModel
import com.hemmersbach.android.viewModel.MainViewModel
import com.hemmersbach.android.viewModel.RemoteViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun mainViewModel(repository: Repository): ViewModel = MainViewModel(repository)

    @Provides
    @IntoMap
    @ViewModelKey(LocalViewModel::class)
    fun localViewModel(repository: Repository): ViewModel = LocalViewModel(repository)

    @Provides
    @IntoMap
    @ViewModelKey(RemoteViewModel::class)
    fun remoteViewModel(repository: Repository): ViewModel = RemoteViewModel(repository)
}