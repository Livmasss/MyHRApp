package com.example.favorite.domain.mappers

import com.example.core.domain.models.Vacancy
import com.example.favorite.domain.models.FavoriteVacancy

internal fun Vacancy.toFavorite() = FavoriteVacancy(
    id = id,
    interestedPeopleCount = interestedPeopleCount,
    title = title,
    city = address.city,
    company = company,
    experienceText = experience.previewText,
    publishDate = publishDate
)