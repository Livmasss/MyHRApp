package com.example.favorite.di

import androidx.room.Room
import com.example.favorite.data.local.FavoriteDAO
import com.example.favorite.data.local.FavoriteVacanciesDatabase
import com.example.favorite.data.local.dataSources.FavoritesLocalDataSource
import com.example.favorite.data.remote.dataSources.FavoritesRemoteDataSource
import com.example.favorite.data.repositories.impl.FavoriteVacanciesRepositoryImpl
import com.example.favorite.domain.repositories.FavoriteVacanciesRepository
import com.example.favorite.domain.useCases.CopyVacanciesToLocalUseCase
import com.example.favorite.domain.useCases.GetFavoriteVacanciesUseCase
import com.example.favorite.presentation.screens.FavoritesViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.ext.getFullName

val favoriteKoinModule = module {
    single<FavoriteVacanciesDatabase> {
        Room.databaseBuilder(
            get(),
            FavoriteVacanciesDatabase::class.java,
            FavoriteVacanciesDatabase::class.getFullName()
        )
            .build()
    }

    single<FavoriteDAO> {
        val db = get<FavoriteVacanciesDatabase>()
        db.getDao()
    }

    singleOf(::FavoritesLocalDataSource)
    singleOf(::FavoritesRemoteDataSource)

    single<FavoriteVacanciesRepository> {
        FavoriteVacanciesRepositoryImpl(get(), get())
    }

    singleOf(::CopyVacanciesToLocalUseCase)
    singleOf(::GetFavoriteVacanciesUseCase)

    viewModelOf(::FavoritesViewModel)
}