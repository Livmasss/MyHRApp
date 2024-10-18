package com.example.vacancies.presentation.navigation

import kotlinx.serialization.Serializable

sealed class VacanciesNavItems {
    @Serializable data object Main: VacanciesNavItems()
    @Serializable data object Details: VacanciesNavItems()
    @Serializable data object Other: VacanciesNavItems()
}
