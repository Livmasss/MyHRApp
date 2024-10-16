package com.example.favorite.presentation.screens

import androidx.lifecycle.ViewModel
import com.example.core.fetchCatching
import com.example.coreui.models.VacancyModel
import com.example.favorite.domain.useCases.GetFavoriteVacanciesUseCase
import com.example.favorite.presentation.mappers.toModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class FavoritesViewModel(
    private val getFavoriteVacanciesUseCase: GetFavoriteVacanciesUseCase
): ViewModel() {
    private val _favoriteList = MutableStateFlow<List<VacancyModel>>(listOf())
    val favoriteList = _favoriteList.asStateFlow()

    fun initiateFavoriteList(scope: CoroutineScope) = scope.fetchCatching(
        onConnectException = {}
    ) {
        _favoriteList.value = getFavoriteVacanciesUseCase.execute().map { it.toModel() }
    }

    fun likeVacancy(vacancy: VacancyModel) {
        if (vacancy !in _favoriteList.value) {
            vacancy.isLiked = true
            _favoriteList.value += vacancy
        }
    }

    fun unlikeVacancy(vacancy: VacancyModel) {
        if (vacancy in _favoriteList.value) {
            _favoriteList.value -= vacancy
            vacancy.isLiked = false

        }
    }
}
