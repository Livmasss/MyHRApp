package com.example.vacancies.presentation.screens.main

import com.example.favorite.domain.useCases.UpdateFavoriteVacanciesUseCase
import com.example.vacancies.domain.useCases.GetVacanciesScreenUseCase
import com.example.vacancies.presentation.mappers.toPresentation
import com.example.vacancies.presentation.models.MainVacanciesScreenModel
import com.example.vacancies.presentation.screens.utils.BaseVacanciesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map

internal class MainVacanciesViewModel(
    private val getVacanciesScreenUseCase: GetVacanciesScreenUseCase,
    updateFavoriteVacanciesUseCase: UpdateFavoriteVacanciesUseCase
): BaseVacanciesViewModel<MainVacanciesScreenModel>(
    updateFavoriteVacanciesUseCase
) {
    override val _favoritesCount: Int
        get() = _screenData.value?.vacancies?.filter { v ->
            v.isFavorite
        }?.size ?: 0

    override fun initiateScreenData(
        scope: CoroutineScope,
        onFavoriteCountChange: (count: Int) -> Unit
    ) {
        initiateScreenData(
            scope = scope,
            onFavoriteCountChange = onFavoriteCountChange,
            data = getVacanciesScreenUseCase.execute().map { it.toPresentation() }
        )
    }

    fun saveFavoriteVacancies(
        scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    ) {
        _screenData.value?.vacancies?.saveFavoriteVacancies(
            scope = scope
        )
    }

    fun setIsFavorite(vacancyIndex: Int, value: Boolean) {
        _screenData.value?.apply {
            val newVacancies = vacancies.setIsFavorite(
                vacancyIndex = vacancyIndex,
                value = value
            )

            _screenData.value = copy(
                vacancies = newVacancies
            )
        }
    }
}