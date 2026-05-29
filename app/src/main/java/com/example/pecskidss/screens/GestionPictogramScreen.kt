package com.example.pecskidss.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pecskidss.viewmodel.PecsViewModel
import com.example.pecskidss.ui.theme.Background
import androidx.compose.runtime.*

@Composable
fun GestionPictogramScreen(viewModel: PecsViewModel) {

    val pictograms by viewModel.pictograms.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(16.dp)
    ) {

        Text("Gestion des pictogrammes")

        Spacer(modifier = Modifier.height(10.dp))

        var currentIndex by remember { mutableStateOf(0) }

        val newPictograms = listOf(
            Triple("Bonbons", "Nourriture", com.example.pecskidss.R.drawable.bonbons),
            Triple("Chocolat", "Nourriture", com.example.pecskidss.R.drawable.chocolat),
            Triple("Oeuf", "Nourriture", com.example.pecskidss.R.drawable.oeuf),
            Triple("Banane", "Nourriture", com.example.pecskidss.R.drawable.banane)
        )

        Button(onClick = {
            viewModel.ensureDefaults()
        }) {
            Text("🔄 Réinitialiser")
        }

        Button(onClick = {

            val pictogram = newPictograms[currentIndex]

            val exists = pictograms.any { it.name == pictogram.first }

            if (!exists) {
                viewModel.addPictogram(
                    name = pictogram.first,
                    category = pictogram.second,
                    imageRes = pictogram.third
                )
            }

            currentIndex = (currentIndex + 1) % newPictograms.size

        }) {
            Text("➕ Ajouter")
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {

            items(pictograms) { item ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(item.imageRes),
                        contentDescription = item.name,
                        modifier = Modifier.size(60.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(item.name)
                        Text(item.category, color = Color.Gray)
                    }

                    Button(onClick = {
                        viewModel.deletePictogram(item)
                    }) {
                        Text("🗑")
                    }
                }
            }
        }
    }
}