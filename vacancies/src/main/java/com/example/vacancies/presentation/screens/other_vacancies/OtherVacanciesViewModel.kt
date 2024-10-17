package com.example.vacancies.presentation.screens.other_vacancies

import androidx.lifecycle.ViewModel
import com.example.core.domain.useCases.SynchronizeFavoritesUseCase
import com.example.core.fetchCatching
import com.example.coreui.mappers.toDomain
import com.example.favorite.domain.useCases.GetFavoriteVacanciesUseCase
import com.example.favorite.domain.useCases.UpdateFavoriteVacanciesUseCase
import com.example.vacancies.domain.useCases.GetVacanciesScreenUseCase
import com.example.vacancies.presentation.mappers.toPresentation
import com.example.vacancies.presentation.models.OtherVacanciesScreenModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class OtherVacanciesViewModel(
    private val getVacanciesScreenUseCase: GetVacanciesScreenUseCase,
    private val updateFavoriteVacanciesUseCase: UpdateFavoriteVacanciesUseCase,
    private val getFavoriteVacanciesUseCase: GetFavoriteVacanciesUseCase,
    private val synchronizeFavoritesUseCase: SynchronizeFavoritesUseCase
): ViewModel() {
    private val _screenState = MutableStateFlow<OtherVacanciesScreenModel?>(null)
    val screenState = _screenState.asStateFlow()

    fun initiateScreen(scope: CoroutineScope) {
        scope.fetchCatching(
            onConnectException = {}
        ) {
            var favoriteIds = getFavoriteVacanciesUseCase.execute().map { it.id }

            getVacanciesScreenUseCase.execute().collect { value ->
                val screen = screenState.value
                if (screen != null)
                    favoriteIds = screen.vacancies.mapNotNull {
                        if (it.isFavorite)
                            it.id
                        else
                            null
                    }

                val synchronizedScreenModel = OtherVacanciesScreenModel (
                    vacancies = synchronizeFavoritesUseCase.execute(
                        remoteVacancies = value.vacancies,
                        favoritesIds = favoriteIds
                    ).map { i -> i.toPresentation() }
                )
                _screenState.value = synchronizedScreenModel
            }
        }
    }

    fun saveFavoriteVacancies(scope: CoroutineScope = CoroutineScope(Dispatchers.IO)) {
        scope.fetchCatching(
            onConnectException = {}
        ) {
            val favoriteOnlyVacancies = _screenState.value?.vacancies?.mapNotNull { v ->
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

    fun setIsFavorite(vacancyIndex: Int, value: Boolean) = _screenState.value?.let {
        val newVacancies = List(it.vacancies.size) { index ->
            it.vacancies[index].copy(
                isFavorite = if (vacancyIndex == index)
                    value
                else
                    it.vacancies[index].isFavorite
            )
        }
        _screenState.value = it.copy(
            vacancies = newVacancies
        )
    }
}