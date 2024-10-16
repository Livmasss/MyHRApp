package com.example.vacancies.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.models.Vacancy
import com.example.core.fetchCatching
import com.example.coreui.mappers.toDomain
import com.example.favorite.domain.useCases.CopyVacanciesToLocalUseCase
import com.example.favorite.domain.useCases.GetFavoriteVacanciesUseCase
import com.example.vacancies.domain.useCases.GetVacanciesScreenUseCase
import com.example.vacancies.presentation.mappers.toPresentation
import com.example.vacancies.presentation.models.MainVacanciesScreenModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest

internal class MainVacanciesViewModel(
    private val getVacanciesScreenUseCase: GetVacanciesScreenUseCase,
    private val copyVacanciesToLocalUseCase: CopyVacanciesToLocalUseCase,
    private val getFavoriteVacanciesUseCase: GetFavoriteVacanciesUseCase
): ViewModel() {
    private val _mainVacanciesScreen = MutableStateFlow<MainVacanciesScreenModel?>(null)
    val mainVacanciesScreen = _mainVacanciesScreen.asStateFlow()

    fun initiateScreenData(scope: CoroutineScope = viewModelScope) {
        scope.fetchCatching(
            onConnectException = {}
        ) {
            val favoriteIds = getFavoriteVacanciesUseCase.execute().map { it.id }
            getVacanciesScreenUseCase.execute().collectLatest {
                val synchronizedScreenModel = it.copy(
                    vacancies = it.vacancies.map { vacancy ->
                        vacancy.copy(
                            isFavorite = vacancy.id in favoriteIds
                        )
                    }
                )
                _mainVacanciesScreen.value = synchronizedScreenModel.toPresentation()
            }
        }
    }

    fun setFavorite(vacancyIndex: Int, value: Boolean) = _mainVacanciesScreen.value?.let {
        val newVacancies = List(it.vacancies.size) { index ->
            it.vacancies[index].copy(
                isFavorite = if (vacancyIndex == index)
                    value
                else
                    it.vacancies[index].isFavorite
            )
        }
        _mainVacanciesScreen.value = it.copy(
            vacancies = newVacancies
        )
    }

    fun saveFavoriteVacancies(scope: CoroutineScope = viewModelScope) {
        scope.fetchCatching(
            onConnectException = {}
        ) {
            val favoriteOnlyVacancies = _mainVacanciesScreen.value?.vacancies?.mapNotNull { v ->
                if (v.isFavorite)
                    v
                else
                    null
            }
            favoriteOnlyVacancies?.let {
                copyVacanciesToLocalUseCase.execute(it.map { it.toDomain() })
            }
        }
    }
}