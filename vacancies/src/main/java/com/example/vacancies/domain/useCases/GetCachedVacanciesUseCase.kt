package com.example.vacancies.domain.useCases

import com.example.core.domain.models.Vacancy
import com.example.vacancies.domain.repositories.VacanciesRepository
import kotlinx.coroutines.flow.Flow

class GetCachedVacanciesUseCase(
    private val vacanciesRepository: VacanciesRepository,
) {
    fun execute(): Flow<List<Vacancy>> {
        return vacanciesRepository.getCachedVacancies()
    }
}