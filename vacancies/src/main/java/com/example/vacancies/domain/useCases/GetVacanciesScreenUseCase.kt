package com.example.vacancies.domain.useCases

import com.example.vacancies.domain.models.VacanciesScreenData
import com.example.vacancies.domain.repositories.VacanciesRepository
import kotlinx.coroutines.flow.Flow

class GetVacanciesScreenUseCase(
    private val vacanciesRepository: VacanciesRepository,
) {
    fun execute(): Flow<VacanciesScreenData> {
        return vacanciesRepository.getVacanciesScreenData()
    }
}