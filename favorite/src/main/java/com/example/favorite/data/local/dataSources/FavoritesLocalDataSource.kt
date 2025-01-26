package com.example.favorite.data.local.dataSources

import android.util.Log
import com.example.favorite.data.local.FavoriteDAO
import com.example.favorite.data.local.entities.FavoriteVacancyEntity


private const val TAG = "FavoritesLocalDataSource"
internal class FavoritesLocalDataSource(
    private val dao: FavoriteDAO
) {
    suspend fun getFavorites(): List<FavoriteVacancyEntity> {
        val result = dao.getFavorites()
        Log.d(TAG, "${result.size} vacancies restored from ${FavoriteVacancyEntity::class.simpleName}")
        return result
    }

    suspend fun saveFavorites(favorites: List<FavoriteVacancyEntity>) {
        dao.clearFavorites()
        dao.saveFavorites(favorites)

        Log.d(TAG, "Saving ${favorites.size} vacancies in ${FavoriteVacancyEntity::class.simpleName}")
    }
}