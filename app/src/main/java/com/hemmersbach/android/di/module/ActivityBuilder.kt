package com.hemmersbach.android.di.module

import com.hemmersbach.android.di.module.fragmentModule.FragmentModule
import com.hemmersbach.android.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun bindMainActivity(): MainActivity
}