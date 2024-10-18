package com.example.vacancies.presentation.screens.other_vacancies

import androidx.lifecycle.ViewModel
import com.example.core.fetchCatching
import com.example.coreui.mappers.toDomain
import com.example.coreui.models.VacancyModel
import com.example.favorite.domain.useCases.UpdateFavoriteVacanciesUseCase
import com.example.vacancies.domain.useCases.GetCachedVacanciesUseCase
import com.example.vacancies.presentation.mappers.toPresentation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class OtherVacanciesViewModel(
    private val getCachedVacanciesUseCase: GetCachedVacanciesUseCase,
    private val updateFavoriteVacanciesUseCase: UpdateFavoriteVacanciesUseCase
): ViewModel() {
    private val _vacancies = MutableStateFlow<List<VacancyModel>?>(null)
    val vacancies = _vacancies.asStateFlow()

    fun initiateScreen(
        scope: CoroutineScope,
        onFavoriteCountChange: (count: Int) -> Unit
    ) {
        scope.fetchCatching(
            onConnectException = {}
        ) {
            getCachedVacanciesUseCase.execute().collect { value ->
                _vacancies.value = value.map {
                    it.toPresentation()
                }

                onFavoriteCountChange(favoritesCount)
            }
        }
    }

    fun saveFavoriteVacancies(scope: CoroutineScope = CoroutineScope(Dispatchers.IO)) {
        scope.fetchCatching(
            onConnectException = {}
        ) {
            val favoriteOnlyVacancies = vacancies.value?.mapNotNull { v ->
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

    fun setIsFavorite(vacancyIndex: Int, value: Boolean) = vacancies.value?.let {
        val newVacancies = List(it.size) { index ->
            it[index].copy(
                isFavorite = if (vacancyIndex == index)
                    value
                else
                    it[index].isFavorite
            )
        }
        _vacancies.value = newVacancies
    }

    val favoritesCount: Int
        get() = _vacancies.value?.filter { it.isFavorite }?.size ?: 0
}