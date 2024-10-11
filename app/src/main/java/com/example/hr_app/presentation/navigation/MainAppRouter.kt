package com.example.hr_app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.favorite.presentation.navigation.FavoriteRouter
import com.example.hr_app.presentation.screens.MessagesScreen
import com.example.hr_app.presentation.screens.ProfileScreen
import com.example.hr_app.presentation.screens.RespondsScreen
import com.example.vacancies.presentation.navigation.VacanciesRouter
import kotlinx.serialization.Serializable

@Composable
fun MainAppRouter() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Main
    ) {
        composable<Main> {
            VacanciesRouter()
        }
        composable<Favorite> {
            FavoriteRouter()
        }
        composable<Responds> {
            RespondsScreen()
        }
        composable<Messages> {
            MessagesScreen()
        }
        composable<Profile> {
            ProfileScreen()
        }
    }
}

@Serializable private data object Main
@Serializable private data object Favorite
@Serializable private data object Responds
@Serializable private data object Messages
@Serializable private data object Profile