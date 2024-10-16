package com.example.favorite.presentation.mappers

import com.example.coreui.models.VacancyModel
import com.example.favorite.domain.models.FavoriteVacancy
import java.util.Calendar


internal fun FavoriteVacancy.toModel() = VacancyModel (
    id = id,
    interestedPeopleCount = interestedPeopleCount,
    title = title,
    city = city,
    isFavorite = true,
    company = company,
    isVerified = true,
    experienceText = experienceText,
    publishDate = Calendar.getInstance().apply {
        time = publishDate
    }
)