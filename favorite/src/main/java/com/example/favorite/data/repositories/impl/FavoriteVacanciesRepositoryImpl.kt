package com.example.favorite.data.repositories.impl

import com.example.favorite.data.local.dataSources.FavoritesLocalDataSource
import com.example.favorite.data.mappers.toData
import com.example.favorite.data.mappers.toDomain
import com.example.favorite.data.remote.dataSources.FavoritesRemoteDataSource
import com.example.favorite.domain.models.FavoriteVacancy
import com.example.favorite.domain.repositories.FavoriteVacanciesRepository

internal class FavoriteVacanciesRepositoryImpl(
    private val localDataSource: FavoritesLocalDataSource,
    private val remoteDataSource: FavoritesRemoteDataSource,
): FavoriteVacanciesRepository {
    override suspend fun readFavorites(): List<FavoriteVacancy> {
        return localDataSource.getFavorites().map { it.toDomain() }
    }

    override suspend fun saveFavorites(favorites: List<FavoriteVacancy>) {
        localDataSource.saveFavorites(
            favorites.map { it.toData() }
        )
    }
}