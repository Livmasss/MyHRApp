package com.example.vacancies.presentation.navigation

import kotlinx.serialization.Serializable

sealed class VacanciesDest {
    @Serializable data object Main: VacanciesDest()
    @Serializable data object Details: VacanciesDest()
    @Serializable data object Other: VacanciesDest()
}
