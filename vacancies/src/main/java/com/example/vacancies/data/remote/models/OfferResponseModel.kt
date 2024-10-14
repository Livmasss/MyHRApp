package com.example.vacancies.data.remote.models

internal data class OfferResponseModel(
    val id: String?,
    val title: String,
    val link: String,
    val offerButton: OfferButton
) {
    data class OfferButton (
        val text: String
    )
}