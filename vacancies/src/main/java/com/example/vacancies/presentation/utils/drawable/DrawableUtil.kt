package com.example.vacancies.presentation.utils.drawable

import com.example.core.domain.models.Recommendation
import com.example.vacancies.R

internal fun Recommendation.getRecommendationIconId() = when(id) {
    "near_vacancies" -> R.drawable.ic_near_vacancies
    "level_up_resume" -> R.drawable.ic_level_up_resume
    "temporary_job" -> R.drawable.ic_temporary_job
    else -> null
}