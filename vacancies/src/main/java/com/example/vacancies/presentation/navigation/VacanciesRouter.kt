package com.example.vacancies.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vacancies.presentation.screens.details.VacancyDetailsScreen
import com.example.vacancies.presentation.screens.main.MainScreen
import com.example.vacancies.presentation.screens.other_vacancies.OtherVacanciesScreen
import kotlinx.serialization.Serializable

@Composable
fun VacanciesRouter() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Main
    ) {
        composable<Main> {
            MainScreen(
                navigateToVacancyDetails = { navController.navigate(Details) },
                navigateToOtherVacancies = { navController.navigate(Other) }
            )
        }
        composable<Details> {
            VacancyDetailsScreen()
        }
        composable<Other> {
            OtherVacanciesScreen(
                onBackButtonClicked = { navController.popBackStack() }
            )
        }
    }
}

@Serializable private data object Main
@Serializable private data object Details
@Serializable private data object Other