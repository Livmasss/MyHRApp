package com.example.vacancies.data.remote.models

internal data class VacanciesResponseModel(
    val recommendations: List<RecommendationResponseModel>?,
    val vacancies: List<VacancyResponseModel>?
)
