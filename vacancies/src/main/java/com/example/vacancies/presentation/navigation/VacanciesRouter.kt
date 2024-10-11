package com.example.vacancies.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vacancies.presentation.screens.VacanciesListScreen
import com.example.vacancies.presentation.screens.VacancyDetailsScreen
import kotlinx.serialization.Serializable

@Composable
fun VacanciesRouter() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = List
    ) {
        composable<List> {
            VacanciesListScreen {
                navController.navigate(route = Details)
            }
        }
        composable<Details> {
            VacancyDetailsScreen()
        }
    }
}

@Serializable private data object List
@Serializable private data object Details