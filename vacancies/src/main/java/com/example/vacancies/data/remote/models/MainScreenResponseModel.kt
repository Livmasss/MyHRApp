package com.example.vacancies.data.remote.models

internal data class MainScreenResponseModel(
    val offers: List<OfferResponseModel>?,
    val vacancies: List<VacancyResponseModel>?
)
