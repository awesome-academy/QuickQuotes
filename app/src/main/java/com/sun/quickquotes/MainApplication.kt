package com.sun.quickquotes

import android.app.Application
import com.sun.quickquotes.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                listOf(
                    apiModule,
                    networkModule,
                    repositoryModule,
                    roomModule,
                    viewModelModule,
                    preferenceModule,
                )
            )
        }
    }
}
