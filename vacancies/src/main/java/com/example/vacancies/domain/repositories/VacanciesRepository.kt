package com.example.vacancies.domain.repositories

import com.example.vacancies.domain.models.VacanciesScreenData

interface VacanciesRepository {
    suspend fun getVacanciesScreenData(): VacanciesScreenData
}