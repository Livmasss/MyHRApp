package com.example.core.domain.models

data class Recommendation (
    val id: String?,
    val title: String,
    val link: String,
    val recommendationButton: RecommendationButton?
) {
    data class RecommendationButton (
        val text: String
    )
}