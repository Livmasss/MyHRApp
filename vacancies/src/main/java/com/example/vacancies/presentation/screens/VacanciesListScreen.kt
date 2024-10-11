package com.example.vacancies.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun VacanciesListScreen(
    navigateToVacancyDetails: () -> Unit
) {
    Text(
        modifier = Modifier
            .clickable { navigateToVacancyDetails() },
        text = "VacanciesListScreen"
    )
}