package com.example.core.domain.useCases

import com.example.core.domain.models.Vacancy
import java.util.UUID

class SynchronizeFavoritesUseCase{
    fun execute(
        remoteVacancies: List<Vacancy>,
        favoritesIds: List<UUID>
    ): List<Vacancy> {
        return remoteVacancies.map { vacancy ->
            vacancy.copy(
                isFavorite = vacancy.id in favoritesIds
            )
        }
    }
}