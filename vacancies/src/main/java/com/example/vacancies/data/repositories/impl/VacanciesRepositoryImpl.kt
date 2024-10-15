package com.example.vacancies.data.repositories.impl

import com.example.vacancies.data.remote.mappers.toDomain
import com.example.vacancies.data.remote.sources.VacanciesRemoteDataSource
import com.example.vacancies.domain.models.VacanciesScreenData
import com.example.vacancies.domain.repositories.VacanciesRepository

internal class VacanciesRepositoryImpl(
    private val remoteDataSource: VacanciesRemoteDataSource
): VacanciesRepository {
    override suspend fun getVacanciesScreenData(): VacanciesScreenData {
        return remoteDataSource.getMainScreenData().toDomain()
    }
}
