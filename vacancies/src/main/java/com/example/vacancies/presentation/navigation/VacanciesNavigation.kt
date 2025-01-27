package com.example.vacancies.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.coreui.navigation.bar.BottomNavDest
import com.example.vacancies.presentation.screens.details.VacancyDetailsScreen
import com.example.vacancies.presentation.screens.main.MainScreen
import com.example.vacancies.presentation.screens.otherVacancies.OtherVacanciesScreen

fun NavGraphBuilder.vacanciesRouter(
    navHostController: NavHostController,
    onFavoriteCountChange: (Int) -> Unit
) {
    navigation<BottomNavDest.Search>(
        startDestination = VacanciesDest.Main
    ) {
        composable<VacanciesDest.Main> {
            MainScreen(
                onVacancyClicked = { navHostController.navigate(VacanciesDest.Details) },
                navigateToOtherVacancies = {
                    navHostController.navigate(VacanciesDest.Other) {
                        popUpTo(VacanciesDest.Other)
                    }
                },
                onFavoriteCountChange = onFavoriteCountChange
            )
        }
        composable<VacanciesDest.Details> {
            VacancyDetailsScreen()
        }
        composable<VacanciesDest.Other> {
            OtherVacanciesScreen(
                navigateToVacancyDetails = {
                    navHostController.navigate(VacanciesDest.Details) {
                        popUpTo(VacanciesDest.Details)
                    }
                },
                onBackButtonClicked = { navHostController.popBackStack() },
                onFavoriteCountChange = onFavoriteCountChange
            )
        }
    }
}