package com.example.pecskidss.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pecskidss.ui.theme.Background
import com.example.pecskidss.ui.theme.DarkText
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.TextUnit
import com.example.pecskidss.viewmodel.PecsViewModel
import androidx.compose.runtime.*

@Composable
fun SettingsScreen(viewModel: PecsViewModel) {

    val voiceEnabled by viewModel.voiceEnabled
    val addJeVeux by viewModel.addJeVeux
    val bigIcons by viewModel.bigIcons

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Paramètres", fontSize = 30.sp, color = DarkText)

        Spacer(modifier = Modifier.height(30.dp))

        // 🔊 Voice
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("🔊 Voix", modifier = Modifier.weight(1f))

            Switch(
                checked = voiceEnabled,
                onCheckedChange = { viewModel.toggleVoice() }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🧠 Je veux
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("🧠 Ajouter les phrases automatiques", modifier = Modifier.weight(1f))

            Switch(
                checked = addJeVeux,
                onCheckedChange = { viewModel.toggleJeVeux() }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 📏 Big icons
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("📏 Gros pictogrammes", modifier = Modifier.weight(1f))

            Switch(
                checked = bigIcons,
                onCheckedChange = { viewModel.toggleBigIcons() }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Mode adapté aux enfants autistes",
            fontSize = 14.sp
        )
    }
}