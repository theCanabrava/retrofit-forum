package com.example.retrofitforum.main

import android.app.Application
import com.example.retrofitforum.di.serviceModule
import com.example.retrofitforum.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApp: Application()
{
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApp)
            modules(
                serviceModule,
                viewModelModule
            )
        }
    }
}