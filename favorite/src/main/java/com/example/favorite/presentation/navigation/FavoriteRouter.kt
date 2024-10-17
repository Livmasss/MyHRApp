package com.example.favorite.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.favorite.presentation.screens.FavoritesListScreen
import kotlinx.serialization.Serializable

@Composable
fun FavoriteRouter() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = FavoriteList
    ) {
        composable<FavoriteList> {
            FavoritesListScreen()
        }
    }
}

@Serializable private data object FavoriteList