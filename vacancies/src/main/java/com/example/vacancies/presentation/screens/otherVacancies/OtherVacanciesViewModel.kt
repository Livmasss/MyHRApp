package com.example.vacancies.presentation.screens.otherVacancies

import com.example.favorite.domain.useCases.UpdateFavoriteVacanciesUseCase
import com.example.vacancies.domain.useCases.GetOtherVacanciesUseCase
import com.example.vacancies.presentation.models.OtherVacanciesScreenModel
import com.example.vacancies.presentation.screens.utils.BaseVacanciesViewModel
import kotlinx.coroutines.CoroutineScope

internal class OtherVacanciesViewModel(
    private val getOtherVacanciesUseCase: GetOtherVacanciesUseCase,
    updateFavoriteVacanciesUseCase: UpdateFavoriteVacanciesUseCase
): BaseVacanciesViewModel<OtherVacanciesScreenModel>(updateFavoriteVacanciesUseCase) {

    override val _favoritesCount: Int
        get() = _screenData.value?.vacancies?.filter { v ->
            v.isFavorite
        }?.size ?: 0

    override fun initiateScreenData(
        scope: CoroutineScope,
        onFavoriteCountChange: (count: Int) -> Unit
    ) {
        initiateScreenData(
            scope,
            onFavoriteCountChange,
            getOtherVacanciesUseCase.execute()
        )
    }

    fun setIsVacancyFavorite(vacancyIndex: Int, value: Boolean) {
        _screenData.value?.apply {
            val newVacancies = super.setIsFavorite(
                vacancyIndex = vacancyIndex,
                value = value
            )

            _screenData.value = newVacancies?.let {
                copy(
                    vacancies = newVacancies
                )
            }
        }
    }
}