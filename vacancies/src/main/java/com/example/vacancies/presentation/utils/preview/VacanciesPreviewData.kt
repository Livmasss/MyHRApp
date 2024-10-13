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
        isLiked = false,
        company = "Company",
        isVerified = false,
        publishData = Calendar.getInstance(),
        experienceText = "Experience from 1 to 3 years"
    ),
    VacancyModel(
        id = UUID.randomUUID(),
        interestedPeopleCount = 10,
        title = "Android разработчик",
        city = "Москва",
        isLiked = true,
        company = "ООО Компания",
        isVerified = true,
        publishData = Calendar.getInstance(),
        experienceText = "Опыт от 3 до 6 лет"
    ),
    VacancyModel(
        id = UUID.randomUUID(),
        interestedPeopleCount = 0,
        title = "Уборщик",
        city = "City",
        isLiked = true,
        company = "Company",
        isVerified = false,
        publishData = Calendar.getInstance(),
        experienceText = "No experience"
    ),
    VacancyModel(
        id = UUID.randomUUID(),
        interestedPeopleCount = 3,
        title = "Java разработчик",
        city = "Санкт-Питербург",
        isLiked = true,
        company = "Ancor",
        isVerified = true,
        publishData = Calendar.getInstance(),
        experienceText = "Опыт от 6 лет"
    ),
)