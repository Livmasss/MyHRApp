package com.example.hr_app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.coreui.navigation.bar.BottomNavItem
import com.example.favorite.presentation.navigation.FavoriteRouter
import com.example.hr_app.presentation.screens.MessagesScreen
import com.example.hr_app.presentation.screens.ProfileScreen
import com.example.hr_app.presentation.screens.RespondsScreen
import com.example.vacancies.presentation.navigation.VacanciesRouter

@Composable
fun MainAppRouter(
    navController: NavHostController,
    onFavoriteCountChange: (count: Int) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Search
    ) {
        composable<BottomNavItem.Search> {
            VacanciesRouter(
                onFavoriteCountChange = onFavoriteCountChange
            )
        }
        composable<BottomNavItem.Favorite> {
            FavoriteRouter(
                onFavoriteCountChange = onFavoriteCountChange
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