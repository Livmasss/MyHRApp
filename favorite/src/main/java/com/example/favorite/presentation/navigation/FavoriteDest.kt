package com.example.favorite.presentation.navigation

import kotlinx.serialization.Serializable


sealed interface FavoriteDest {
    @Serializable data object FavoriteList: FavoriteDest
}
