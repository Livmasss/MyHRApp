package com.example.vacancies.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.fetchCatching
import com.example.vacancies.domain.useCases.GetVacanciesScreenUseCase
import com.example.vacancies.presentation.mappers.toPresentation
import com.example.vacancies.presentation.models.MainVacanciesScreenModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class MainVacanciesViewModel(
    private val getVacanciesScreenUseCase: GetVacanciesScreenUseCase
): ViewModel() {
    private val _mainVacanciesScreen = MutableStateFlow<MainVacanciesScreenModel?>(null)
    val mainVacanciesScreen = _mainVacanciesScreen.asStateFlow()

    fun getVacanciesScreenData(scope: CoroutineScope = viewModelScope) {
        scope.fetchCatching(
            onConnectException = {}
        ) {
            val result = getVacanciesScreenUseCase.execute()
            _mainVacanciesScreen.value = result?.toPresentation()
        }
    }
}