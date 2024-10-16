package com.example.vacancies.data.remote.dataSources

import com.example.core.data.remote.dataSources.takeBody
import com.example.vacancies.data.remote.VacanciesApi
import com.example.vacancies.data.remote.models.VacanciesResponseModel

internal class VacanciesRemoteDataSource(
    private val api: VacanciesApi
) {
    suspend fun getMainScreenData(): VacanciesResponseModel {
        val response = api.getMainScreenInfo()
        return response.takeBody()
    }
}