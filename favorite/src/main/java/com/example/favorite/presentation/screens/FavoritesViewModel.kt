package com.example.favorite.presentation.screens

import androidx.lifecycle.ViewModel
import com.example.core.fetchCatching
import com.example.coreui.mappers.toDomain
import com.example.coreui.models.VacancyModel
import com.example.favorite.domain.useCases.GetFavoriteVacanciesUseCase
import com.example.favorite.domain.useCases.UpdateFavoriteVacanciesUseCase
import com.example.favorite.presentation.mappers.toModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class FavoritesViewModel(
    private val getFavoriteVacanciesUseCase: GetFavoriteVacanciesUseCase,
    private val updateFavoriteVacanciesUseCase: UpdateFavoriteVacanciesUseCase
): ViewModel() {
    private val _favoriteList = MutableStateFlow<List<VacancyModel>>(listOf())
    val favoriteList = _favoriteList.asStateFlow()

    fun initiateFavoriteList(scope: CoroutineScope) = scope.fetchCatching(
        onConnectException = {}
    ) {
        _favoriteList.value = getFavoriteVacanciesUseCase.execute().map { it.toModel() }
    }

    fun likeVacancy(index: Int) {
        if (index < _favoriteList.value.size ) {
            val vacancy = _favoriteList.value[index]
            vacancy.isFavorite = true
            _favoriteList.value += vacancy
        }
    }

    fun unlikeVacancy(index: Int) {
        if (index < _favoriteList.value.size ) {
            val vacancy = _favoriteList.value[index]
            _favoriteList.value -= vacancy
            vacancy.isFavorite = false
        }
    }


    fun saveFavoriteVacancies(scope: CoroutineScope = CoroutineScope(Dispatchers.IO)) {
        scope.fetchCatching(
            onConnectException = {}
        ) {
            updateFavoriteVacanciesUseCase.execute(favoriteList.value.map { it.toDomain() })
        }
    }
}
