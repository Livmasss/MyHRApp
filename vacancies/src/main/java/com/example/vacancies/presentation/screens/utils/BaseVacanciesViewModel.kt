package com.example.vacancies.presentation.screens.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.fetchCatching
import com.example.coreui.mappers.toDomain
import com.example.coreui.models.VacancyModel
import com.example.favorite.domain.useCases.UpdateFavoriteVacanciesUseCase
import com.example.vacancies.presentation.models.BaseVacanciesScreenModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest

abstract class BaseVacanciesViewModel<T: BaseVacanciesScreenModel>(
    private val updateFavoriteVacanciesUseCase: UpdateFavoriteVacanciesUseCase
): ViewModel() {
    protected val _screenData = MutableStateFlow<T?>(null)
    val screenData = _screenData.asStateFlow()

    protected var _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()

    val favoritesCount: Int
        get() = _favoritesCount
    protected abstract val _favoritesCount: Int

    abstract fun initiateScreenData(
        scope: CoroutineScope = viewModelScope,
        onConnectionFailed: () -> Unit,
        onFavoriteCountChange: (count: Int) -> Unit
    )

    protected fun initiateScreenData(
        scope: CoroutineScope = viewModelScope,
        onFavoriteCountChange: (count: Int) -> Unit,
        onConnectionFailed: () -> Unit,
        data: Flow<T>
    ) {
        scope.fetchCatching(
            onConnectException = onConnectionFailed
        ) {
            _loading.value = true
            data.collectLatest {
                _loading.value = false
                _screenData.value = it
                onFavoriteCountChange(favoritesCount)
            }
        }
    }

    fun saveFavoriteVacancies(
        scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    ) {
        scope.fetchCatching(
            onConnectException = {}
        ) {
            val favoriteOnlyVacancies = _screenData.value?.vacancies?.mapNotNull { v ->
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

    protected fun setIsFavorite(
        vacancyIndex: Int,
        value: Boolean
    ): List<VacancyModel>? {
        return _screenData.value?.vacancies?.run {
            List(size) { index ->
                this[index].copy(
                    isFavorite = if (vacancyIndex == index)
                        value
                    else
                        this[index].isFavorite
                )
            }
        }
    }
}