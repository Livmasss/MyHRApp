package com.example.vacancies.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.models.Vacancy
import com.example.core.fetchCatching
import com.example.favorite.domain.useCases.CopyVacanciesToLocalUseCase
import com.example.vacancies.domain.useCases.GetVacanciesScreenUseCase
import com.example.vacancies.presentation.mappers.toPresentation
import com.example.vacancies.presentation.models.MainVacanciesScreenModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest

internal class MainVacanciesViewModel(
    private val getVacanciesScreenUseCase: GetVacanciesScreenUseCase,
    private val copyVacanciesToLocalUseCase: CopyVacanciesToLocalUseCase
): ViewModel() {
    private val _mainVacanciesScreen = MutableStateFlow<MainVacanciesScreenModel?>(null)
    val mainVacanciesScreen = _mainVacanciesScreen.asStateFlow()

    fun initiateScreenData(scope: CoroutineScope = viewModelScope) {
        scope.fetchCatching(
            onConnectException = {}
        ) {
            getVacanciesScreenUseCase.execute().collectLatest {
                _mainVacanciesScreen.value = it.toPresentation()
                saveFavoriteVacancies(it.vacancies)
            }
        }
    }

    private suspend fun saveFavoriteVacancies(vacancies: List<Vacancy>) {
        val favoriteOnlyVacancies = vacancies.mapNotNull { v ->
            if (v.isFavorite)
                v
            else
                null
        }
        copyVacanciesToLocalUseCase.execute(favoriteOnlyVacancies)
    }
}