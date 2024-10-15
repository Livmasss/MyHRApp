package com.example.hr_app.di

import android.content.Context
import com.example.vacancies.di.vacanciesKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication

fun KoinApplication.configureKoin(context: Context) {
    androidLogger()
    androidContext(context)
    modules(
        vacanciesKoinModule
    )
}