package com.hemmersbach.android.di.module.fragmentModule

import com.hemmersbach.android.ui.fragments.Local
import com.hemmersbach.android.ui.fragments.Remote
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun provideLocalFactory(): Local

    @ContributesAndroidInjector
    abstract fun RemoteProvider(): Remote
}