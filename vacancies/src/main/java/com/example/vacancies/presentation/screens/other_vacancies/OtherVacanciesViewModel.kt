package com.example.vacancies.presentation.screens.other_vacancies

import androidx.lifecycle.ViewModel
import com.example.core.fetchCatching
import com.example.vacancies.domain.useCases.GetVacanciesScreenUseCase
import com.example.vacancies.presentation.mappers.toPresentation
import com.example.vacancies.presentation.models.OtherVacanciesScreenModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class OtherVacanciesViewModel(
    private val getVacanciesScreenUseCase: GetVacanciesScreenUseCase
): ViewModel() {
    private val _screenState = MutableStateFlow<OtherVacanciesScreenModel?>(null)
    val screenState = _screenState.asStateFlow()

    fun initiateScreen(scope: CoroutineScope) {
        scope.fetchCatching(
            onConnectException = {}
        ) {
            getVacanciesScreenUseCase.execute().collect {
                _screenState.value = OtherVacanciesScreenModel( it.vacancies.map { i -> i.toPresentation() })
            }
        }
    }
}