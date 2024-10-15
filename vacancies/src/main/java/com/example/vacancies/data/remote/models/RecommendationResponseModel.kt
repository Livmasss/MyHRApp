package com.example.vacancies.data.remote.models

import com.google.gson.annotations.SerializedName

internal data class RecommendationResponseModel(
    val id: String?,
    val title: String,
    val link: String,
    @SerializedName("offerButton")
    val recommendationButton: RecommendationButtonResponseModel?
) {
    data class RecommendationButtonResponseModel (
        val text: String
    )
}