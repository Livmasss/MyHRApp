package com.example.hr_app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.coreui.navigation.bar.BottomNavDest
import com.example.favorite.presentation.navigation.FavoriteDest
import com.example.favorite.presentation.screens.FavoritesListScreen
import com.example.hr_app.presentation.screens.MessagesScreen
import com.example.hr_app.presentation.screens.ProfileScreen
import com.example.hr_app.presentation.screens.RespondsScreen
import com.example.vacancies.presentation.navigation.VacanciesDest
import com.example.vacancies.presentation.screens.details.VacancyDetailsScreen
import com.example.vacancies.presentation.screens.main.MainScreen
import com.example.vacancies.presentation.screens.otherVacancies.OtherVacanciesScreen

@Composable
fun MainAppRouter(
    navController: NavHostController,
    onFavoriteCountChange: (count: Int) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavDest.Search
    ) {
        navigation<BottomNavDest.Search>(
            startDestination = VacanciesDest.Main
        ) {
            composable<VacanciesDest.Main> {
                MainScreen(
                    onVacancyClicked = { navController.navigate(VacanciesDest.Details) },
                    navigateToOtherVacancies = { navController.navigate(VacanciesDest.Other) },
                    onFavoriteCountChange = onFavoriteCountChange
                )
            }
            composable<VacanciesDest.Details> {
                VacancyDetailsScreen()
            }
            composable<VacanciesDest.Other> {
                OtherVacanciesScreen(
                    navigateToVacancyDetails = { navController.navigate(VacanciesDest.Details) },
                    onBackButtonClicked = { navController.popBackStack() },
                    onFavoriteCountChange = onFavoriteCountChange
                )
            }
        }

        navigation<BottomNavDest.Favorite>(
            startDestination = FavoriteDest.FavoriteList
        ) {
            composable<FavoriteDest.FavoriteList> {
                FavoritesListScreen(
                    onFavoriteCountChange = onFavoriteCountChange,
                    onItemClicked = { navController.navigate(VacanciesDest.Details) },
                )
            }
        }

        composable<BottomNavDest.Responds> {
            RespondsScreen()
        }
        composable<BottomNavDest.Messages> {
            MessagesScreen()
        }
        composable<BottomNavDest.Profile> {
            ProfileScreen()
        }
    }
}