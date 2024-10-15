package com.example.hr_app.presentation

import android.app.Application
import com.example.hr_app.di.configureKoin
import org.koin.core.context.startKoin

class HRApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            configureKoin(this@HRApplication)
        }
    }
}