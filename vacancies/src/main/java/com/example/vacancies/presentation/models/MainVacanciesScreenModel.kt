package com.example.vacancies.presentation.models

import com.example.coreui.models.VacancyModel

internal data class MainVacanciesScreenModel(
    val recommendations: List<RecommendationModel>?,
    val vacancies: List<VacancyModel>
)