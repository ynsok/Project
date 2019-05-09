package com.hemmersbach.android.DI.Component

import android.app.Application
import com.hemmersbach.android.DI.Module.ActivityBuilder
import com.hemmersbach.android.DI.Module.AppModule
import com.hemmersbach.android.NetworkingApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class, AndroidSupportInjectionModule::class])
interface AppComponent {
    fun inject(networkingApp: NetworkingApp)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        @BindsInstance
        fun application(application: Application): Builder
    }
}