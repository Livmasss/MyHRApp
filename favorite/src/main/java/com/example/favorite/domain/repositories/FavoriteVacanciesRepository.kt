package com.example.favorite.domain.repositories

import com.example.favorite.domain.models.FavoriteVacancy

interface FavoriteVacanciesRepository {
    suspend fun saveFavorites(favorites: List<FavoriteVacancy>)
    suspend fun readFavorites(): List<FavoriteVacancy>
}