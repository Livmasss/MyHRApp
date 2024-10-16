package com.example.favorite.presentation.screens

import com.example.coreui.models.VacancyModel
import com.example.favorite.domain.useCases.CopyVacanciesToLocalUseCase
import kotlinx.coroutines.flow.MutableStateFlow

internal class FavoritesViewModel(
    private val copyVacanciesToLocalUseCase: CopyVacanciesToLocalUseCase
) {
    private val _likedList = MutableStateFlow<List<VacancyModel>>(listOf())

    fun likeVacancy(vacancy: VacancyModel) {
        if (vacancy !in _likedList.value) {
            vacancy.isLiked = true
            _likedList.value += vacancy
        }
    }

    fun unlikeVacancy(vacancy: VacancyModel) {
        if (vacancy in _likedList.value) {
            _likedList.value -= vacancy
            vacancy.isLiked = false

        }
    }
}