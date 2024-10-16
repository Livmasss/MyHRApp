package com.example.favorite.data.mappers

import com.example.favorite.data.local.entities.FavoriteVacancyEntity
import com.example.favorite.domain.models.FavoriteVacancy
import java.util.Calendar

internal fun FavoriteVacancyEntity.toDomain() = FavoriteVacancy (
    id = id,
    interestedPeopleCount = interestedPeopleCount,
    title = title,
    city = city,
    company = company,
    experienceText = experienceText,
    publishDate = Calendar.getInstance().time,
)

internal fun FavoriteVacancy.toData() = FavoriteVacancyEntity(
    id = id,
    interestedPeopleCount = interestedPeopleCount,
    title = title,
    city = city,
    company = company,
    experienceText = experienceText,
//    publishDate = publishDate
)