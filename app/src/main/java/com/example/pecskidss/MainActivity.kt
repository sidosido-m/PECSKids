package com.example.pecskidss

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.example.pecskidss.navigation.AppNavigation
import com.example.pecskidss.viewmodel.PecsViewModel
import kotlinx.coroutines.launch
import com.example.pecskidss.data.PecsDatabase
import com.example.pecskidss.data.PictogramEntity
import kotlinx.coroutines.flow.first

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao = PecsDatabase.getDatabase(application).pictogramDao()

        lifecycleScope.launch {

            val names = dao.getAllPictograms()
                .first()
                .map { it.name }

            if (!names.contains("Eau")) {
                dao.insert(PictogramEntity(0, "Eau", "Boissons", R.drawable.eau))
            }

            if (!names.contains("Pain")) {
                dao.insert(PictogramEntity(0, "Pain", "Nourriture", R.drawable.pain))
            }

            if (!names.contains("Pomme")) {
                dao.insert(PictogramEntity(0, "Pomme", "Nourriture", R.drawable.pomme))
            }

            if (!names.contains("Jouer")) {
                dao.insert(PictogramEntity(0, "Jouer", "Actions", R.drawable.jouer))
            }
            if (!names.contains("Lait")) {
                dao.insert(PictogramEntity(0, "Lait", "Boissons", R.drawable.lait))
            }
        }

        setContent {
            AppNavigation()
        }
    }
}