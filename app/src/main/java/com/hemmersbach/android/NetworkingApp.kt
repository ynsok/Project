package com.hemmersbach.android

import android.app.Activity
import android.app.Application
import android.util.Log
import com.hemmersbach.android.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class NetworkingApp : Application(), HasActivityInjector {
    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this)
        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }

    private class ReleaseTree : Timber.Tree() {

        override fun isLoggable(tag: String?, priority: Int): Boolean {
            if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
                return false
            }
            return true
        }

        override fun log(priority: Int, tag: String?, message: String?, t: Throwable?) {
            if (!isLoggable(tag, priority)) {
                return
            }

            if (priority == Log.WARN) {
                Log.w(tag, message)
            } else if (priority == Log.ERROR) {
                Log.e(tag, message)
            }
        }
    }
}