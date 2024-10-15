package com.example.vacancies.data.remote.mappers

import com.example.vacancies.data.remote.models.RecommendationResponseModel
import com.example.vacancies.data.remote.models.VacanciesResponseModel
import com.example.vacancies.data.remote.models.VacancyResponseModel
import com.example.vacancies.domain.models.Address
import com.example.vacancies.domain.models.Experience
import com.example.vacancies.domain.models.Recommendation
import com.example.vacancies.domain.models.VacanciesScreenData
import com.example.vacancies.domain.models.Vacancy

internal fun VacanciesResponseModel.toDomain(): VacanciesScreenData = VacanciesScreenData (
    recommendations = recommendations?.map { it.toDomain() },
    vacancies = vacancies?.map { it.toDomain() } ?: listOf()
)

internal fun VacancyResponseModel.toDomain() = Vacancy(
    id = id,
    interestedPeopleCount = lookingNumber,
    title = title,
    address = address.toDomain(),
    company = company,
    experience = experience.toDomain(),
    publishDate = publishDate,
    isFavorite = isFavorite
)

internal fun RecommendationResponseModel.toDomain() = Recommendation(
    id = id,
    title = title,
    link = link,
    recommendationButton = recommendationButton?.toDomain()
)

internal fun RecommendationResponseModel.RecommendationButtonResponseModel.toDomain() = Recommendation.RecommendationButton(
    text = text
)

internal fun VacancyResponseModel.ExperienceResponseModel.toDomain() = Experience (
    previewText = previewText,
    text = text
)

internal fun VacancyResponseModel.AddressResponseModel.toDomain() = Address(
    city = city,
    street = street,
    house = house
)
