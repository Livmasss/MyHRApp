package com.example.vacancies.domain.models

data class VacanciesScreenData(
    val recommendations: List<Recommendation>?,
    val vacancies: List<Vacancy>,
)
