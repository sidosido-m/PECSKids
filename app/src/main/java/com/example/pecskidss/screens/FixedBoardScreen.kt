package com.example.pecskidss.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pecskidss.R
import com.example.pecskidss.ui.theme.Background

data class FixedItem(val name: String, val imageRes: Int)

@Composable
fun FixedBoardScreen() {

    val items = listOf(
        FixedItem("Eau", R.drawable.eau),
        FixedItem("Pain", R.drawable.pain),
        FixedItem("Pomme", R.drawable.pomme),
        FixedItem("Jouer", R.drawable.jouer),
        FixedItem("Sports", R.drawable.sports),
        FixedItem("Lait",  R.drawable.lait),
        FixedItem("Maman", R.drawable.maman),
        FixedItem("Papa", R.drawable.papa),
        FixedItem("Frère", R.drawable.frere),
        FixedItem("Sœur",  R.drawable.soeur),
        FixedItem("Heureux", R.drawable.heureux),
        FixedItem("Triste", R.drawable.triste),
        FixedItem("En colère", R.drawable.colere),
        FixedItem("Fatigué",  R.drawable.fatigue),
        FixedItem("Jus",  R.drawable.jus),
        FixedItem("Boissons",  R.drawable.boissons),
        FixedItem("Cafe",  R.drawable.cafe),
        FixedItem("Grand-Pére",  R.drawable.grand_pere),
        FixedItem("Grand-Mére",  R.drawable.grand_mere),


    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(16.dp)
    ) {

        Text("Tableau Fixe", fontSize = 28.sp)

        Spacer(modifier = Modifier.height(20.dp))

        LazyVerticalGrid(columns = GridCells.Fixed(2)) {

            items(items) { item ->

                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .background(Color(0xFF4CAF50))
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = painterResource(item.imageRes),
                        contentDescription = item.name,
                        modifier = Modifier.size(100.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(item.name, color = Color.White)
                }
            }
        }
    }
}