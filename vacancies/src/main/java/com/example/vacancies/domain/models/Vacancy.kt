package com.example.vacancies.domain.models

import com.google.gson.annotations.SerializedName
import java.util.Date
import java.util.UUID

data class Vacancy (
    val id: UUID,
    val interestedPeopleCount: Int,
    val title: String,
    val address: Address,
    val company: String,
    val experience: Experience,
    @SerializedName("publishedDate")
    val publishDate: Date,
    val isFavorite: Boolean

)
