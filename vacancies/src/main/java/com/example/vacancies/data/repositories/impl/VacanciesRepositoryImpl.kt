package com.example.vacancies.data.repositories.impl

import com.example.core.domain.models.Vacancy
import com.example.favorite.domain.repositories.FavoriteVacanciesRepository
import com.example.vacancies.data.local.VacanciesLocalDataSource
import com.example.vacancies.data.remote.dataSources.VacanciesRemoteDataSource
import com.example.vacancies.data.remote.mappers.toDomain
import com.example.vacancies.domain.models.MainVacanciesScreenData
import com.example.vacancies.domain.models.OtherVacanciesScreenData
import com.example.vacancies.domain.repositories.VacanciesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class VacanciesRepositoryImpl(
    private val localVacanciesDataSource: VacanciesLocalDataSource,
    private val remoteVacanciesDataSource: VacanciesRemoteDataSource,
    private val favoriteRepository: FavoriteVacanciesRepository
): VacanciesRepository {
    override fun getVacanciesScreenData(): Flow<MainVacanciesScreenData> = flow {
        val fetchResult = remoteVacanciesDataSource.getMainScreenData()
        val synchronized = fetchResult.toDomain().synchronizeWithLocalFavorites()

        emit(synchronized)
        localVacanciesDataSource.cacheFetchedVacancies(synchronized.vacancies)
    }

    override fun getOtherVacancies(): Flow<OtherVacanciesScreenData> = flow {
        val cached = localVacanciesDataSource.getCachedVacancies()
        cached?.let {
            emit(
                OtherVacanciesScreenData(
                    vacancies = it.synchronizeWithLocalFavorites()
                )
            )
        }
    }

    private suspend fun MainVacanciesScreenData.synchronizeWithLocalFavorites(): MainVacanciesScreenData {
        val synchronizedVacancies = vacancies.synchronizeWithLocalFavorites()
        return copy(
            vacancies = synchronizedVacancies
        )
    }

    private suspend fun List<Vacancy>.synchronizeWithLocalFavorites(): List<Vacancy> {
        val favoritesIds = favoriteRepository.readFavorites().map { it.id }

        val synchronizedVacancies = map { vacancy ->
            vacancy.copy(
                isFavorite = vacancy.id in favoritesIds
            )
        }
        return synchronizedVacancies
    }
}
