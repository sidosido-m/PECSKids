package com.example.pecskidss.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pecskidss.viewmodel.PecsViewModel
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.draw.shadow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.TextUnit

@Composable
fun BuilderScreen(
    viewModel: PecsViewModel
) {

    val background = Color(0xFFF8F5F0)

    val selectedWords by viewModel.selectedWords.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(16.dp)
    ) {

        Text(
            text = "Construction de phrases",
            fontSize = 26.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 🧠 Phrase box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .shadow(
                    elevation = 6.dp,
                    shape = RoundedCornerShape(16.dp)
                )
                .background(
                    Color.White,
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = viewModel.getSentence().ifEmpty {
                    "Ajoute des pictogrammes..."
                },
                fontSize = 22.sp,
                color = Color(0xFF4CAF50)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🧹 Actions
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Button(onClick = {
                viewModel.clearSentence()
            }) {
                Text("🧹 Effacer")
            }

            Button(onClick = {
                viewModel.speakSentence()
            }) {
                Text("🔊 Parler")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Éléments ajoutés",
            fontSize = 18.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(10.dp))

        // 🟢 Words row
        val size = if (viewModel.bigIcons.value) 22.sp else 16.sp
        LazyRow {

            items(selectedWords) { word ->

                WordChip(
                    word = word,
                    fontSize = size,
                    onRemove = {
                        viewModel.removeWord(word)
                    }
                )
            }
        }
    }
}
@Composable
private fun WordChip(
    word: String,
    fontSize: TextUnit,
    onRemove: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(6.dp)
            .background(
                Color(0xFF4CAF50),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onRemove() }
            .padding(horizontal = 18.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = word,
            color = Color.White,
            fontSize = fontSize
        )
    }
}
