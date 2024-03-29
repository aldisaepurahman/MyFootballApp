package com.example.footballapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.footballapp.core.di.databaseModule
import com.example.footballapp.core.di.networkModule
import com.example.footballapp.core.di.repositoryModule
import com.example.footballapp.di.useCaseModule
import com.example.footballapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}