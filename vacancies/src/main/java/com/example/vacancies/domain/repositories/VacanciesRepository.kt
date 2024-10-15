package com.example.vacancies.domain.repositories

import com.example.vacancies.domain.models.VacanciesScreenData
import kotlinx.coroutines.flow.Flow

interface VacanciesRepository {
    fun getVacanciesScreenData(): Flow<VacanciesScreenData>
}