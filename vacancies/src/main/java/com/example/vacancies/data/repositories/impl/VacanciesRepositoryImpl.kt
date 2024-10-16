package com.example.vacancies.data.repositories.impl

import com.example.vacancies.data.local.VacanciesLocalDataSource
import com.example.vacancies.data.remote.mappers.toDomain
import com.example.vacancies.data.remote.dataSources.VacanciesRemoteDataSource
import com.example.vacancies.domain.models.VacanciesScreenData
import com.example.vacancies.domain.repositories.VacanciesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class VacanciesRepositoryImpl(
    private val localDataSource: VacanciesLocalDataSource,
    private val remoteDataSource: VacanciesRemoteDataSource,
): VacanciesRepository {
    override fun getVacanciesScreenData(): Flow<VacanciesScreenData> = flow {
        val cached = localDataSource.fetchedVacancies
        cached?.let {
            emit(
                VacanciesScreenData(
                    recommendations = null,
                    vacancies = it.map { i -> i.toDomain() }
                )
            )
        }

        val fetchResult = remoteDataSource.getMainScreenData()
        emit(fetchResult.toDomain())
        fetchResult.vacancies?.let { localDataSource.cacheFetchedVacancies(it) }
    }
}
