package com.example.vacancies.data.remote.models

import com.google.gson.annotations.SerializedName
import java.util.Date
import java.util.UUID

internal data class VacancyResponseModel (
    val id: UUID,
    val lookingNumber: Int,
    val title: String,
    val address: AddressResponseModel,
    val company: String,
    val experience: ExperienceResponseModel,
    @SerializedName("publishedDate")
    val publishData: Date,
    val isFavorite: Boolean

//    TODO("Добавить остальные поля")
) {
    data class AddressResponseModel (
        @SerializedName("town")
        val city: String,
        val street: String,
        val house: String,
    )

    data class ExperienceResponseModel (
        val previewText: String,
        val text: String
    )
}
