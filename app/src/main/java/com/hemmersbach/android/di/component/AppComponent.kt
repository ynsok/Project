package com.hemmersbach.android.di.component

import android.app.Application
import com.hemmersbach.android.di.module.ActivityBuilder
import com.hemmersbach.android.di.module.AppModule
import com.hemmersbach.android.di.module.DatabaseModule
import com.hemmersbach.android.di.module.NetworkModule
import com.hemmersbach.android.di.module.RepositoryModule
import com.hemmersbach.android.di.module.ViewModelModule
import com.hemmersbach.android.NetworkingApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class,
        AndroidSupportInjectionModule::class, DatabaseModule::class, NetworkModule::class, RepositoryModule::class, ViewModelModule::class]
)
interface AppComponent {
    fun inject(networkingApp: NetworkingApp)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        @BindsInstance
        fun application(application: Application): Builder
    }
}