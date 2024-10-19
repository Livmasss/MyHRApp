package com.example.vacancies.domain.useCases

import com.example.vacancies.domain.models.MainVacanciesScreenData
import com.example.vacancies.domain.repositories.VacanciesRepository
import kotlinx.coroutines.flow.Flow

class GetVacanciesScreenUseCase(
    private val vacanciesRepository: VacanciesRepository,
) {
    fun execute(): Flow<MainVacanciesScreenData> {
        return vacanciesRepository.getVacanciesScreenData()
    }
}