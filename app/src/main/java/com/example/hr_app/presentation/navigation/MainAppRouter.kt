package com.example.hr_app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.coreui.navigation.bar.BottomNavDest
import com.example.favorite.presentation.navigation.favoriteRouter
import com.example.hr_app.presentation.screens.MessagesScreen
import com.example.hr_app.presentation.screens.ProfileScreen
import com.example.hr_app.presentation.screens.RespondsScreen
import com.example.vacancies.presentation.navigation.VacanciesDest
import com.example.vacancies.presentation.navigation.vacanciesRouter

@Composable
fun MainAppRouter(
    navController: NavHostController,
    onFavoriteCountChange: (count: Int) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavDest.Search
    ) {
        vacanciesRouter(navController, onFavoriteCountChange)

        favoriteRouter(navController, onFavoriteCountChange) {
            navController.navigate(VacanciesDest.Details)
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