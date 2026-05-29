package com.example.pecskidss.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.navigation.NavController
import com.example.pecskidss.navigation.Screen
import com.example.pecskidss.ui.theme.*
import androidx.compose.ui.unit.dp

@Composable
fun SplashScreen(
    navController: NavController
) {

    LaunchedEffect(Unit) {

        delay(2500)

        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "PECS Kids",
                fontSize = 42.sp,
                color = PrimaryGreen
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Communication for autistic children",
                color = DarkText
            )
        }
    }
}