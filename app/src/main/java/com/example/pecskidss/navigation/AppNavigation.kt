package com.example.pecskidss.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pecskidss.screens.*
import com.example.pecskidss.viewmodel.PecsViewModel

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    // 🟢 Shared ViewModel
    val viewModel: PecsViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        // 🟢 Splash
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }

        // 🟢 Home
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        // 🟢 Library
        composable(Screen.Library.route) {
            LibraryScreen(viewModel)
        }

        // 🟢 Builder
        composable(Screen.Builder.route) {
            BuilderScreen(viewModel)
        }

        // 🟢 Fixed Board
        composable(Screen.FixedBoard.route) {
            FixedBoardScreen()
        }

        // 🟢 Settings
        composable(Screen.Settings.route) {
            SettingsScreen(viewModel = viewModel)
        }

        // 🟢 Manage pictograms
        composable(Screen.Manage.route) {
            GestionPictogramScreen(viewModel)
        }
    }
}