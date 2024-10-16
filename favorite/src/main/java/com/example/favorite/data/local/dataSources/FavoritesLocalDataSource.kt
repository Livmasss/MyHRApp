package com.example.favorite.data.local.dataSources

import com.example.favorite.data.local.FavoriteDAO
import com.example.favorite.data.local.entities.FavoriteVacancyEntity

internal class FavoritesLocalDataSource(
    private val dao: FavoriteDAO
) {
    suspend fun getFavorites(): List<FavoriteVacancyEntity> {
        return dao.getFavorites()
    }

    suspend fun saveFavorites(favorites: List<FavoriteVacancyEntity>) {
        dao.clearFavorites()
        dao.saveFavorites(favorites)
    }
}