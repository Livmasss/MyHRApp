package com.example.favorite.domain.useCases

import com.example.core.domain.models.Vacancy
import com.example.favorite.domain.mappers.toFavorite
import com.example.favorite.domain.repositories.FavoriteVacanciesRepository

class UpdateFavoriteVacanciesUseCase(
    private val repository: FavoriteVacanciesRepository
) {
    suspend fun execute(remoteVacancies: List<Vacancy>) {
        repository.saveFavorites(
            remoteVacancies.map { it.toFavorite() }
        )
    }
}