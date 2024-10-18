package com.example.vacancies.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.fetchCatching
import com.example.coreui.mappers.toDomain
import com.example.favorite.domain.useCases.UpdateFavoriteVacanciesUseCase
import com.example.vacancies.domain.useCases.GetVacanciesScreenUseCase
import com.example.vacancies.presentation.mappers.toPresentation
import com.example.vacancies.presentation.models.MainVacanciesScreenModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest

internal class MainVacanciesViewModel(
    private val getVacanciesScreenUseCase: GetVacanciesScreenUseCase,
    private val updateFavoriteVacanciesUseCase: UpdateFavoriteVacanciesUseCase
): ViewModel() {
    private val _mainVacanciesScreen = MutableStateFlow<MainVacanciesScreenModel?>(null)
    val mainVacanciesScreen = _mainVacanciesScreen.asStateFlow()

    private var _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()

    fun initiateScreenData(
        scope: CoroutineScope = viewModelScope,
        onFavoriteCountChange: (count: Int) -> Unit
    ) {
        scope.fetchCatching(
            onConnectException = {}
        ) {
            _loading.value = true
            getVacanciesScreenUseCase.execute().collectLatest {
                _loading.value = false
                _mainVacanciesScreen.value = it.toPresentation()
            }
            onFavoriteCountChange(favoritesCount)
        }
    }

    fun setIsFavorite(vacancyIndex: Int, value: Boolean) = _mainVacanciesScreen.value?.let {
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

    fun saveFavoriteVacancies(scope: CoroutineScope = CoroutineScope(Dispatchers.IO)) {
        scope.fetchCatching(
            onConnectException = {}
        ) {
            val favoriteOnlyVacancies = _mainVacanciesScreen.value?.vacancies?.mapNotNull { v ->
                if (v.isFavorite)
                    v
                else
                    null
            }
            favoriteOnlyVacancies?.let { favorites ->
                updateFavoriteVacanciesUseCase.execute(favorites.map { it.toDomain() })
            }
        }
    }

    val favoritesCount: Int
        get() = _mainVacanciesScreen.value?.vacancies?.filter { v ->
        v.isFavorite
    }?.size ?: 0
}