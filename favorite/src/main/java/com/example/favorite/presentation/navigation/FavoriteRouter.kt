package com.example.favorite.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.coreui.models.VacancyModel
import com.example.coreui.navigation.bar.BottomNavDest
import com.example.favorite.presentation.screens.FavoritesListScreen

fun NavGraphBuilder.favoriteRouter(
    navHostController: NavHostController,
    onFavoriteCountChange: (Int)-> Unit,
    onVacancyClick: (VacancyModel) -> Unit
    ) {
    navigation<BottomNavDest.Favorite>(
        startDestination = FavoriteDest.FavoriteList
    ) {
        composable<FavoriteDest.FavoriteList> {
            FavoritesListScreen(
                onFavoriteCountChange = onFavoriteCountChange,
                onItemClicked = onVacancyClick,
            )
        }
    }
}