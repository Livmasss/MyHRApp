package com.example.vacancies.domain.useCases

import com.example.vacancies.domain.repositories.VacanciesRepository
import com.example.vacancies.presentation.mappers.toPresentation
import com.example.vacancies.presentation.models.OtherVacanciesScreenModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetOtherVacanciesUseCase(
    private val vacanciesRepository: VacanciesRepository,
) {
    fun execute(): Flow<OtherVacanciesScreenModel> {
        return vacanciesRepository.getOtherVacancies().map { it.toPresentation() }
    }
}