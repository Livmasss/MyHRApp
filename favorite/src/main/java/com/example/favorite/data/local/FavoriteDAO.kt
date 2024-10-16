package com.example.favorite.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.favorite.data.local.entities.FavoriteVacancyEntity

@Dao
interface FavoriteDAO {
    @Query("SELECT * FROM favorite_vacancies")
    suspend fun getFavorites(): List<FavoriteVacancyEntity>

    @Insert
    suspend fun saveFavorites(favorites: List<FavoriteVacancyEntity>)

    @Query("DELETE FROM favorite_vacancies")
    suspend fun clearFavorites()
}