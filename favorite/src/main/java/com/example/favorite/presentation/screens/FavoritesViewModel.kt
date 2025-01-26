package com.example.favorite.presentation.screens

import android.util.Log
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

private const val TAG = "FavoritesViewModel"
internal class FavoritesViewModel(
    private val getFavoriteVacanciesUseCase: GetFavoriteVacanciesUseCase,
    private val updateFavoriteVacanciesUseCase: UpdateFavoriteVacanciesUseCase
): ViewModel() {
    private val _favoriteList = MutableStateFlow<List<VacancyModel>>(listOf())
    val favoriteList = _favoriteList.asStateFlow()

    fun initiateFavoriteList(
        scope: CoroutineScope,
        onFavoriteCountChange: (count: Int) -> Unit
    ) {
        if (_favoriteList.value.isNotEmpty())
            return

        Log.d(TAG, "initiateFavoriteList")
        scope.fetchCatching(
            onConnectException = {}
        ) {
            _favoriteList.value = getFavoriteVacanciesUseCase.execute().map { it.toModel() }
            onFavoriteCountChange(favoriteList.value.size)
        }
    }

    fun unlikeVacancy(index: Int) {
        Log.d(TAG, "unlikeVacancy called")

        if (index < favoriteList.value.size ) {
            val vacancy = favoriteList.value[index]
            _favoriteList.value -= vacancy
            vacancy.isFavorite = false
        }
    }

    fun saveFavoriteVacancies(scope: CoroutineScope = CoroutineScope(Dispatchers.IO)) {
        Log.d(TAG, "saveFavoriteVacancies called")

        scope.fetchCatching(
            onConnectException = {}
        ) {
            updateFavoriteVacanciesUseCase.execute(favoriteList.value.map { it.toDomain() })
        }
    }
}
