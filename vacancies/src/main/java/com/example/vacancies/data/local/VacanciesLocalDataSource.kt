package com.example.vacancies.data.local

import com.example.vacancies.data.remote.models.VacancyResponseModel

internal class VacanciesLocalDataSource {
    // replace this implementation with some good caching
    private var _fetchedVacancies: List<VacancyResponseModel>? = null
    val fetchedVacancies: List<VacancyResponseModel>?
        get() = _fetchedVacancies

    fun cacheFetchedVacancies(vacancies: List<VacancyResponseModel>) {
        _fetchedVacancies = vacancies
    }
    fun clearFetchedVacancies() {
        _fetchedVacancies = null
    }
}