package com.example.vacancies.presentation.mappers

import com.example.core.domain.models.Recommendation
import com.example.core.domain.models.Vacancy
import com.example.coreui.models.VacancyModel
import com.example.vacancies.domain.models.VacanciesScreenData
import com.example.vacancies.presentation.models.MainVacanciesScreenModel
import com.example.vacancies.presentation.models.RecommendationModel
import com.example.vacancies.presentation.utils.drawable.getRecommendationIconId
import java.util.Calendar

internal fun VacanciesScreenData.toPresentation() = MainVacanciesScreenModel (
    recommendations = recommendations?.map { it.toPresentation() },
    vacancies = vacancies.map { it.toPresentation() },
)

internal fun Vacancy.toPresentation() = VacancyModel (
    id = id,
    interestedPeopleCount = interestedPeopleCount,
    title = title,
    city = address.city,
    isLiked = isFavorite,
    company = company,
    isVerified = true,
    experienceText = experience.previewText,
    publishDate = Calendar.getInstance().apply { time = publishDate }
)

internal fun Recommendation.toPresentation() = RecommendationModel(
    id = id.toString(),
    iconId = getRecommendationIconId(),
    title = title,
    link = link,
    buttonText = recommendationButton?.text
)