package com.example.vacancies.presentation.utils.preview

import com.example.coreui.models.VacancyModel
import java.util.Calendar
import java.util.UUID

internal val vacanciesPreviewList = listOf(
    VacancyModel(
        id = UUID.randomUUID(),
        interestedPeopleCount = 1,
        title = "Title",
        city = "City",
        isFavorite = false,
        company = "Company",
        isVerified = false,
        publishDate = Calendar.getInstance(),
        experienceText = "Experience from 1 to 3 years"
    ),
    VacancyModel(
        id = UUID.randomUUID(),
        interestedPeopleCount = 10,
        title = "Android разработчик",
        city = "Москва",
        isFavorite = true,
        company = "ООО Компания",
        isVerified = true,
        publishDate = Calendar.getInstance(),
        experienceText = "Опыт от 3 до 6 лет"
    ),
    VacancyModel(
        id = UUID.randomUUID(),
        interestedPeopleCount = 0,
        title = "Уборщик",
        city = "City",
        isFavorite = true,
        company = "Company",
        isVerified = false,
        publishDate = Calendar.getInstance(),
        experienceText = "No experience"
    ),
    VacancyModel(
        id = UUID.randomUUID(),
        interestedPeopleCount = 3,
        title = "Java разработчик",
        city = "Санкт-Питербург",
        isFavorite = true,
        company = "Ancor",
        isVerified = true,
        publishDate = Calendar.getInstance(),
        experienceText = "Опыт от 6 лет"
    ),
)