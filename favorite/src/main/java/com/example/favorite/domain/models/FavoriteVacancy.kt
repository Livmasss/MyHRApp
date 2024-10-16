package com.example.favorite.domain.models

import java.util.Date
import java.util.UUID

data class FavoriteVacancy(
    val id: UUID,
    val interestedPeopleCount: Int,
    val title: String,
    val city: String,
    val company: String,
    val experienceText: String,
    val publishDate: Date,
)
