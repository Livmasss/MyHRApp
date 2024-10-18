package com.example.hr_app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.coreui.navigation.bar.BottomNavItem
import com.example.favorite.presentation.navigation.FavoriteRouter
import com.example.favorite.presentation.screens.FavoritesListScreen
import com.example.hr_app.presentation.screens.MessagesScreen
import com.example.hr_app.presentation.screens.ProfileScreen
import com.example.hr_app.presentation.screens.RespondsScreen
import com.example.vacancies.presentation.navigation.VacanciesNavItems
import com.example.vacancies.presentation.screens.details.VacancyDetailsScreen
import com.example.vacancies.presentation.screens.main.MainScreen
import com.example.vacancies.presentation.screens.other_vacancies.OtherVacanciesScreen

@Composable
fun MainAppRouter(
    navController: NavHostController,
    onFavoriteCountChange: (count: Int) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Search
    ) {
        navigation<BottomNavItem.Search>(
            startDestination = VacanciesNavItems.Main
        ) {
            composable<VacanciesNavItems.Main> {
                MainScreen(
                    onVacancyClicked = { navController.navigate(VacanciesNavItems.Details) },
                    navigateToOtherVacancies = { navController.navigate(VacanciesNavItems.Other) },
                    onFavoriteCountChange = onFavoriteCountChange
                )
            }
            composable<VacanciesNavItems.Details> {
                VacancyDetailsScreen()
            }
            composable<VacanciesNavItems.Other> {
                OtherVacanciesScreen(
                    navigateToVacancyDetails = { navController.navigate(VacanciesNavItems.Details) },
                    onBackButtonClicked = { navController.popBackStack() },
                    onFavoriteCountChange = onFavoriteCountChange
                )
            }
        }
        composable<BottomNavItem.Favorite> {
            FavoriteRouter(
                onFavoriteCountChange = onFavoriteCountChange,
                navigateToVacancyDetails = { navController.navigate(VacanciesNavItems.Details) }
            )
        }

        composable<BottomNavItem.Favorite> {
            FavoritesListScreen(
                onFavoriteCountChange = onFavoriteCountChange,
                onItemClicked = { navController.navigate(VacanciesNavItems.Details) }
            )
        }

        composable<BottomNavItem.Responds> {
            RespondsScreen()
        }
        composable<BottomNavItem.Messages> {
            MessagesScreen()
        }
        composable<BottomNavItem.Profile> {
            ProfileScreen()
        }
    }
}