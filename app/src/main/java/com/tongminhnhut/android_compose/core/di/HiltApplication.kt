package com.tongminhnhut.android_compose.core.di

import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import androidx.multidex.BuildConfig
import androidx.multidex.MultiDexApplication

@HiltAndroidApp
class HiltApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}