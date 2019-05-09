package com.hemmersbach.android.DI.Module

import android.util.Log
import com.hemmersbach.android.UI.Fragments.Remote
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class RemoteProvider {

    init {
        Log.i("AppfRemoteProvider", this.toString())
    }

    @ContributesAndroidInjector
    abstract fun RemoteProvider(): Remote
}