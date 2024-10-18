package com.example.vacancies.data.local

import com.example.core.domain.models.Vacancy

internal class VacanciesLocalDataSource {
    // replace this implementation with some good caching
    private var _fetchedVacancies: List<Vacancy>? = null
    fun getCachedVacancies() = _fetchedVacancies

    fun cacheFetchedVacancies(vacancies: List<Vacancy>) {
        _fetchedVacancies = vacancies
    }
    fun clearFetchedVacancies() {
        _fetchedVacancies = null
    }
}