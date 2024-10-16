package com.example.coreui.models

import androidx.compose.runtime.Stable
import java.util.Calendar
import java.util.UUID

@Stable
data class VacancyModel(
    val id: UUID,
    val interestedPeopleCount: Int?,
    val title: String,
    val city: String,
    var isLiked: Boolean,
    val company: String,
    val isVerified: Boolean,
    val experienceText: String,
    val publishDate: Calendar
)
