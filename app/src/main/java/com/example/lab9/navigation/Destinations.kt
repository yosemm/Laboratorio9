package com.example.lab9.navigation

sealed class Destinations(val route: String) {
    data object Wishlist : Destinations("wishlist")
    data object Profile : Destinations("profile")
}