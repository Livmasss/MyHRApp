package com.example.vacancies.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vacancies.presentation.screens.details.VacancyDetailsScreen
import com.example.vacancies.presentation.screens.main.MainScreen
import com.example.vacancies.presentation.screens.other_vacancies.OtherVacanciesScreen
import kotlinx.serialization.Serializable

@Composable
fun VacanciesRouter(
    navController: NavHostController = rememberNavController(),
    onFavoriteCountChange: (count: Int) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Main
    ) {
        composable<Main> {
            MainScreen(
                navigateToVacancyDetails = { navController.navigate(Details) },
                navigateToOtherVacancies = { navController.navigate(Other) },
                onFavoriteCountChange = onFavoriteCountChange
            )
        }
        composable<Details> {
            VacancyDetailsScreen()
        }
        composable<Other> {
            OtherVacanciesScreen(
                navigateToVacancyDetails = { navController.navigate(Details) },
                onBackButtonClicked = { navController.popBackStack() },
                onFavoriteCountChange = onFavoriteCountChange
            )
        }
    }
}

@Serializable private data object Main
@Serializable private data object Details
@Serializable private data object Other