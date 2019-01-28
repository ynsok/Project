package com.hemmersbach.android

import android.app.Application
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class MyApplication : Application(), KodeinAware {

    override val kodein by Kodein.lazy {

        bind<OkHttpClient>() with singleton {
            val okHttpClient = OkHttpClient()
            okHttpClient
        }
    }
}