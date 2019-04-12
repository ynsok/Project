package com.hemmersbach.android

import android.app.Application
import android.arch.persistence.room.Room
import com.hemmersbach.android.Database.JokesDatabase
import com.hemmersbach.android.JokesRepository.JokesRepository
import com.hemmersbach.android.NetworkProvider.JokesApiService
import com.hemmersbach.android.SharedPreferences.SharedPreferences
import com.hemmersbach.android.Util.ConnectionManager
import com.hemmersbach.android.ViewModel.LocalViewModelFactory
import com.hemmersbach.android.ViewModel.RemoteViewModelFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application(), KodeinAware {

    override val kodein by Kodein.lazy {

        bind() from singleton { GsonConverterFactory.create() }
        bind() from singleton { CoroutineCallAdapterFactory() }
        bind() from singleton { OkHttpClient.Builder().build() }
        bind() from singleton {
            Retrofit.Builder()
                .baseUrl(JokesApiService.BASE_URL)
                .addCallAdapterFactory(instance())
                .addConverterFactory(instance())
                .client(instance())
                .build()
        }
        bind() from singleton { instance<Retrofit>().create(JokesApiService::class.java) }
        bind() from singleton { ConnectionManager(this@MyApplication) }
        bind() from singleton { SharedPreferences(this@MyApplication) }
        bind() from singleton {
            Room.databaseBuilder(
                this@MyApplication,
                JokesDatabase::class.java,
                "joke.db"
            ).build()
        }
        bind() from singleton { instance<JokesDatabase>().jokesDao() }
        bind() from singleton { JokesRepository(instance(), instance(), instance(), instance()) }
        bind() from provider { RemoteViewModelFactory(instance()) }
        bind() from provider { LocalViewModelFactory(instance()) }
    }
}