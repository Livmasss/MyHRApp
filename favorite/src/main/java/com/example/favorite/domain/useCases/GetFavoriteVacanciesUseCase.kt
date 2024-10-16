package com.example.favorite.domain.useCases

import com.example.favorite.domain.repositories.FavoriteVacanciesRepository

class GetFavoriteVacanciesUseCase(
    private val repository: FavoriteVacanciesRepository
) {
    suspend fun execute() =
        repository.getFavorites()
}