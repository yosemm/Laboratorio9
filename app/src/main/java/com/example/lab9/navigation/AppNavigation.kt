package com.example.lab9.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lab9.feature.profile.presentation.ProfileScreen
import com.example.lab9.feature.wishlist.presentation.WishlistScreen
import com.example.lab9.feature.wishlist.presentation.WishlistViewModel

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    // ViewModel con alcance del NavBackStackEntry del grafo raíz
    val wishlistViewModel: WishlistViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Destinations.Wishlist.route
    ) {
        composable(Destinations.Wishlist.route) {
            WishlistScreen(
                viewModel = wishlistViewModel,
                onNavigateToProfile = {
                    navController.navigate(Destinations.Profile.route)
                }
            )
        }

        composable(Destinations.Profile.route) {
            ProfileScreen(
                viewModel = wishlistViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
