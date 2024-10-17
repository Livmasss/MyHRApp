package com.example.hr_app.di

import com.example.core.domain.useCases.SynchronizeFavoritesUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val coreKoinModule = module {
    singleOf(::SynchronizeFavoritesUseCase)
}