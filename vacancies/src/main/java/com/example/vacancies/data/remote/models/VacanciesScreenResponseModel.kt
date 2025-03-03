package com.example.vacancies.data.remote.models

import com.google.gson.annotations.SerializedName

internal data class VacanciesScreenResponseModel(
    @SerializedName("vacancies")
    val vacancies: List<VacancyResponseModel>?,
    @SerializedName("offers")
    val recommendations: List<RecommendationResponseModel>?,
)
