package com.example.vacancies.domain.models

import com.example.core.domain.models.Recommendation
import com.example.core.domain.models.Vacancy

data class VacanciesScreenData(
    val recommendations: List<Recommendation>?,
    val vacancies: List<Vacancy>,
)
