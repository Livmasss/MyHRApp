package com.example.favorite.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.favorite.data.local.entities.FavoriteVacancyEntity

@Database(
    entities = [FavoriteVacancyEntity::class],
    version = 1
)
abstract class FavoriteVacanciesDatabase: RoomDatabase() {
    abstract fun getDao(): FavoriteDAO
}