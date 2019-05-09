package com.hemmersbach.android.DI.Module

import android.util.Log
import com.hemmersbach.android.UI.Fragments.Local
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LocalProvider {
    init {
        Log.i("AppfLocalProvider", this.toString())
    }

    @ContributesAndroidInjector
    abstract fun provideLocalFactory(): Local
}