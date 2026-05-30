package com.example.pecskidss.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pecskidss.navigation.Screen
import com.example.pecskidss.ui.components.LargeButton
import com.example.pecskidss.ui.theme.*


@Composable
fun HomeScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "PECS Kids",
            fontSize = 36.sp,
            color = DarkText
        )
        Text(
            text = "Communication pour enfants non verbaux",
            fontSize = 14.sp,
            color = DarkText
        )

        Spacer(modifier = Modifier.height(40.dp))

        LargeButton("Construction Libre", PrimaryGreen) {
            navController.navigate(Screen.Builder.route)
        }

        LargeButton("Tableau Fixe", AccentOrange) {
            navController.navigate(Screen.FixedBoard.route)
        }

        LargeButton("Bibliothèque", PrimaryGreen) {
            navController.navigate(Screen.Library.route)
        }

        LargeButton("Paramètres", NeutralGray) {
            navController.navigate(Screen.Settings.route)
        }

        LargeButton("Historique", PrimaryGreen) {
            navController.navigate(Screen.History.route)
        }

        LargeButton("Gestion", BlueGray) {
            navController.navigate(Screen.Manage.route)
        }
    }
}