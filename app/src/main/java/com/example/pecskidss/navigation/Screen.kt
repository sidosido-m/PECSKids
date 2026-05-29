package com.example.pecskidss.navigation

sealed class Screen(val route: String) {

    object Home : Screen("home")
    object Library : Screen("library")
    object Builder : Screen("builder")
    object Settings : Screen("settings")
    object FixedBoard : Screen("fixed_board")
    object Manage : Screen("manage")
    object Splash : Screen("splash")
}