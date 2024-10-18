package com.example.vacancies.domain.useCases

import android.util.Log
import com.example.core.TAG_DATA_REMOTE
import com.example.core.domain.models.Vacancy
import com.example.vacancies.domain.models.VacanciesScreenData
import com.example.vacancies.domain.repositories.VacanciesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.ConnectException

class GetCachedVacanciesUseCase(
    private val vacanciesRepository: VacanciesRepository,
) {
    fun execute(): Flow<List<Vacancy>> {
        return try {
            vacanciesRepository.getCachedVacancies()
        }
        catch (e: ConnectException) {
            throw e
        }
        catch (e: Exception) {
            Log.e(TAG_DATA_REMOTE, "Some error occurred: ${e.stackTrace}")
            flow {  }
        }
    }
}