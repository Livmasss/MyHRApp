package com.example.coreui.models

import java.util.Calendar
import java.util.UUID

data class VacancyModel(
    val id: UUID,
    val interestedPeopleCount: Int?,
    val title: String,
    val city: String,
    val isLiked: Boolean,
    val company: String,
    val isVerified: Boolean,
    val experienceText: String,
    val publishData: Calendar
)
