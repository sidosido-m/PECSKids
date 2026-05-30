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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import com.example.pecskidss.data.PictogramEntity

@Composable
fun GestionPictogramScreen(viewModel: PecsViewModel) {

    val pictograms by viewModel.pictograms.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var selectedPictogram by remember { mutableStateOf<PictogramEntity?>(null) }

    var editName by remember { mutableStateOf("") }
    var editCategory by remember { mutableStateOf("") }

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
            Triple("Banane", "Nourriture", com.example.pecskidss.R.drawable.banane),
            Triple("Jus D'ananas", "Boissons", com.example.pecskidss.R.drawable.jus_dananas),
            Triple("Jus De Tomate", "Boissons", com.example.pecskidss.R.drawable.jus_de_tomate),
            Triple("Jus De Pomme", "Boissons", com.example.pecskidss.R.drawable.jus_de_pomme),
            Triple("Jus D'orange", "Boissons", com.example.pecskidss.R.drawable.jus_dorange),
            Triple("D'epart", "Actions", com.example.pecskidss.R.drawable.depart),
            Triple("Escalader", "Actions", com.example.pecskidss.R.drawable.escalader),
            Triple("Terrain De Sports", "Actions", com.example.pecskidss.R.drawable.terrain_de_sports),
            Triple("Velodrome", "Actions", com.example.pecskidss.R.drawable.velodrome),
            Triple("Piste De Ski", "Actions", com.example.pecskidss.R.drawable.piste_de_ski ),
            Triple("Aire De Joux", "Actions", com.example.pecskidss.R.drawable.aire_de_jeux ),
            Triple("Joux En Bois", "Actions", com.example.pecskidss.R.drawable.jeux_en_bois ),
            Triple("Joux De Societe", "Actions", com.example.pecskidss.R.drawable.jeux_de_societe ),
            Triple("Joux Educatifs", "Actions", com.example.pecskidss.R.drawable.jeux_educatifs ),
            Triple("Parc De Jeux", "Actions", com.example.pecskidss.R.drawable.parc_de_jeux ),
            Triple("Café Glace", "Boissons", com.example.pecskidss.R.drawable.cafe_glace),
            Triple("Glace Pilee", "Boissons", com.example.pecskidss.R.drawable.glace_pilee),
            Triple("Lait Vegetal", "Boissons", com.example.pecskidss.R.drawable.lait_vegetal),
            Triple("Lait A l'amande", "Boissons", com.example.pecskidss.R.drawable.lait_a_lamande),

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

                    Button(
                        onClick = {
                            selectedPictogram = item
                            editName = item.name
                            editCategory = item.category
                            showDialog = true
                        }
                    ) {
                        Text("✏️")
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    Button(onClick = {
                        viewModel.deletePictogram(item)
                    }) {
                        Text("🗑")
                    }
                }
            }
        }
    }
    if (showDialog && selectedPictogram != null) {

        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },

            title = {
                Text("Modifier pictogramme")
            },

            text = {

                Column {

                    OutlinedTextField(
                        value = editName,
                        onValueChange = { editName = it },
                        label = { Text("Nom") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editCategory,
                        onValueChange = { editCategory = it },
                        label = { Text("Catégorie") }
                    )
                }
            },

            confirmButton = {

                TextButton(
                    onClick = {

                        viewModel.updatePictogram(
                            selectedPictogram!!.copy(
                                name = editName,
                                category = editCategory
                            )
                        )

                        showDialog = false
                    }
                ) {
                    Text("Enregistrer")
                }
            },

            dismissButton = {

                TextButton(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("Annuler")
                }
            }
        )
    }
}