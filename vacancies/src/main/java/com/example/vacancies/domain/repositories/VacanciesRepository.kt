package com.example.vacancies.domain.repositories

import com.example.vacancies.domain.models.MainVacanciesScreenData
import com.example.vacancies.domain.models.OtherVacanciesScreenData
import kotlinx.coroutines.flow.Flow

interface VacanciesRepository {
    fun getVacanciesScreenData(): Flow<MainVacanciesScreenData>
    fun getOtherVacancies(): Flow<OtherVacanciesScreenData>
}