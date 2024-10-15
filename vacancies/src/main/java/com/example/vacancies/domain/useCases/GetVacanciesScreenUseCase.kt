package com.example.vacancies.domain.useCases

import com.example.vacancies.domain.models.VacanciesScreenData
import com.example.vacancies.domain.repositories.VacanciesRepository
import java.net.ConnectException

class GetVacanciesScreenUseCase(
    private val vacanciesRepository: VacanciesRepository,
) {
    suspend fun execute(): VacanciesScreenData? {
        return try {
            vacanciesRepository.getVacanciesScreenData()
        }
        catch (e: ConnectException) {
            throw e
        }
        catch (e: Exception) {
            null
        }
    }
}