package com.example.vacancies.domain.useCases

import android.util.Log
import com.example.core.TAG_DATA_REMOTE
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
            Log.e(TAG_DATA_REMOTE, "Some error occurred: ${e}")
            null
        }
    }
}