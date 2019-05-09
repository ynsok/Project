package com.hemmersbach.android.DI.Module

import android.app.Application
import android.arch.persistence.room.Room
import android.util.Log

import com.hemmersbach.android.Database.JokesDatabase
import com.hemmersbach.android.NetworkProvider.JokesApiService
import com.hemmersbach.android.SharedPreferences.SharedPreferences
import com.hemmersbach.android.Util.ConnectionManager
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    init {
        Log.i("AppfModul", this.toString())
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory() = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideCoroutineCallAdapterFactory() = CoroutineCallAdapterFactory()

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofit(
        baseURL: String,
        client: OkHttpClient,
        converterFactory: GsonConverterFactory,
        coroutineCallAdapterFactory: CoroutineCallAdapterFactory
    ) =
        Retrofit.Builder()
            .baseUrl(baseURL)
            .addCallAdapterFactory(coroutineCallAdapterFactory)
            .addConverterFactory(converterFactory)
            .client(client)
            .build().create(JokesApiService::class.java)

    @Provides
    @Singleton
    fun provideRoomDb(application: Application) =
        Room.databaseBuilder(application, JokesDatabase::class.java, "joke.db").build()

    @Provides
    @Singleton
    fun provideDao(jokesDatabase: JokesDatabase) = jokesDatabase.jokesDao()

    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application) = SharedPreferences(application)

    @Provides
    @Singleton
    fun provideConnectionManager(application: Application) = ConnectionManager(application)

    @Provides
    @Singleton
    fun provideBaseUrl() = "http://api.icndb.com/jokes/random/"
}