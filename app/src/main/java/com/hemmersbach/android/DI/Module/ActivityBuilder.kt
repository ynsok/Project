package com.hemmersbach.android.DI.Module

import android.util.Log
import com.hemmersbach.android.UI.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    init {
        Log.i("AppfActivityProvider", this.toString())
    }

    @ContributesAndroidInjector(modules = [RemoteProvider::class, LocalProvider::class])
    abstract fun bindMainActivity(): MainActivity
}