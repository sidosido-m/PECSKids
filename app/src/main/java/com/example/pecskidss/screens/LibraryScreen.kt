package com.example.pecskidss.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pecskidss.data.PictogramEntity
import com.example.pecskidss.viewmodel.PecsViewModel
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState

@Composable
fun LibraryScreen(viewModel: PecsViewModel) {

    val background = Color(0xFFF8F5F0)

    // 🟢 بيانات من Room
    val pictograms by viewModel.pictograms.collectAsState()

    // 🧠 الجملة الحالية (PECS sentence)
    val selectedWords by viewModel.selectedWords.collectAsState()

    var selectedCategory by remember { mutableStateOf("Toutes") }

    // 🟢 فلترة حسب التصنيف
    val filteredPictograms = remember(pictograms, selectedCategory) {
        if (selectedCategory == "Toutes") {
            pictograms
        } else {
            pictograms.filter { it.category == selectedCategory }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(16.dp)
    ) {

        // 🟡 Title
        Text(
            text = "Bibliothèque PECS",
            fontSize = 28.sp,
            color = Color(0xFF212121)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // 🧠 Phrase Builder (live)
        Text(
            text = if (selectedWords.isEmpty())
                "Appuie sur un pictogramme..."
            else
                selectedWords.joinToString(" "),
            fontSize = 22.sp,
            color = Color(0xFF4CAF50)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 🟢 Filtres catégories
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            Button(onClick = { selectedCategory = "Toutes" }) {
                Text("Toutes")
            }

            Button(onClick = { selectedCategory = "Boissons" }) {
                Text("Boissons")
            }

            Button(onClick = { selectedCategory = "Nourriture" }) {
                Text("Nourriture")
            }

            Button(onClick = { selectedCategory = "Actions" }) {
                Text("Actions")
            }
            Button(onClick = { selectedCategory = "Sentiments" }) {
                Text("Sentiments")
            }
            Button(onClick = { selectedCategory = "Famille" }) {
                Text("Famille")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // 🟢 Grid pictogrammes (Room data)
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize()
        ) {

            items(filteredPictograms) { item ->

                PictogramCard(
                    item = item,
                    onClick = {
                        viewModel.addWord(item.name)
                    }
                )
            }
        }
    }
}

@Composable
private fun PictogramCard(
    item: PictogramEntity,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .padding(10.dp)
            .background(Color(0xFF4CAF50))
            .clickable { onClick() }
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.name,
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = item.name,
            color = Color.White,
            fontSize = 18.sp
        )
    }
}