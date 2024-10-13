package com.example.vacancies.presentation.models

import androidx.annotation.DrawableRes

data class RecommendationModel (
    val id: String,
    @DrawableRes
    val iconId: Int?,
    val title: String,
    val link: String,
    val buttonText: String? = null
)