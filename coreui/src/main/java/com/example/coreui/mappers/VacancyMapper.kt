package com.example.coreui.mappers

import com.example.core.domain.models.Address
import com.example.core.domain.models.Experience
import com.example.core.domain.models.Vacancy
import com.example.coreui.models.VacancyModel


fun VacancyModel.toDomain() = Vacancy(
    id = id,
    interestedPeopleCount = interestedPeopleCount ?: 0,
    title = title,
    address = Address(
        city = city,
        street = "",
        house = ""
    ),
    company = company,
    experience = Experience(
        previewText = experienceText,
        text = ""
    ),
    publishDate = publishDate.time,
    isFavorite = isFavorite
)
