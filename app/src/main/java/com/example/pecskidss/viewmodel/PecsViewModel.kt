package com.example.pecskidss.viewmodel

import android.app.Application
import android.speech.tts.TextToSpeech
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pecskidss.data.PecsDatabase
import com.example.pecskidss.data.PictogramEntity
import com.example.pecskidss.R
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Locale
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.first
import com.example.pecskidss.data.CommunicationHistory


class PecsViewModel(application: Application) : AndroidViewModel(application) {

    // 🟢 DATABASE
    private val dao = PecsDatabase
        .getDatabase(application)
        .pictogramDao()

    private val historyDao = PecsDatabase
        .getDatabase(application)
        .historyDao()

    val history =
        historyDao.getAllHistory()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

    fun addPictogramOnce(name: String, category: String, imageRes: Int) {
        viewModelScope.launch {

            val exists = dao.getByName(name)

            if (exists == null) {
                dao.insert(
                    PictogramEntity(name = name, category = category, imageRes = imageRes)
                )
            }
        }
    }

    val pictograms: StateFlow<List<PictogramEntity>> =
        dao.getAllPictograms()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

    var voiceEnabled = mutableStateOf(true)
    var addJeVeux = mutableStateOf(true)
    var bigIcons = mutableStateOf(true)
    // 🧠 PHRASE STATE (PECS CORE)
    private val _selectedWords = MutableStateFlow<List<String>>(emptyList())
    val selectedWords: StateFlow<List<String>> = _selectedWords.asStateFlow()

    // 🔊 TTS
    private var tts: TextToSpeech? = null

    init {
        tts = TextToSpeech(application) { status ->
            if (status == TextToSpeech.SUCCESS) {

                val result = tts?.setLanguage(Locale.FRENCH)

                if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED
                ) {
                    tts?.setLanguage(Locale.US)
                }

                tts?.setPitch(1.0f)
                tts?.setSpeechRate(0.9f)
            }
        }
    }

    // ➕ ADD PICTOGRAM
    fun addPictogram(name: String, category: String, imageRes: Int) {
        viewModelScope.launch {
            dao.insert(
                PictogramEntity(
                    name = name,
                    category = category,
                    imageRes = imageRes
                )
            )
        }
    }

    fun ensureDefaults() {
        viewModelScope.launch {

            val pictos = dao.getAllPictograms().first()
            val names = pictos.map { it.name }

            val defaults = listOf(
                Triple("Eau", "Boissons", R.drawable.eau),
                Triple("Pain", "Nourriture", R.drawable.pain),
                Triple("Pomme", "Nourriture", R.drawable.pomme),
                Triple("Jouer", "Actions", R.drawable.jouer),
                Triple("Lait", "Boissons", R.drawable.lait),

                Triple("Heureux", "Sentiments", R.drawable.heureux),
                Triple("Triste", "Sentiments", R.drawable.triste),
                Triple("En colère", "Sentiments", R.drawable.colere),
                Triple("Fatigué", "Sentiments", R.drawable.fatigue),

                Triple("Maman", "Famille", R.drawable.maman),
                Triple("Papa", "Famille", R.drawable.papa),
                Triple("Frère", "Famille", R.drawable.frere),
                Triple("Sœur", "Famille", R.drawable.soeur)
            )

            defaults.forEach { (name, cat, img) ->
                if (!names.contains(name)) {
                    dao.insert(PictogramEntity(0, name, cat, img))
                }
            }
        }
    }

    // ❌ DELETE
    fun deletePictogram(pictogram: PictogramEntity) {
        viewModelScope.launch {
            dao.delete(pictogram)
        }
    }

    // ✏️ UPDATE
    fun updatePictogram(pictogram: PictogramEntity) {
        viewModelScope.launch {
            dao.update(pictogram)
        }
    }

    // 🧠 ADD WORD (PECS)
    fun addWord(word: String) {
        _selectedWords.update { it + word }
    }

    // ❌ REMOVE WORD (safe version)
    fun removeWord(word: String) {
        _selectedWords.update { current ->
            current.toMutableList().apply {
                remove(word)
            }
        }
    }

    // 🧹 CLEAR
    fun clearSentence() {
        _selectedWords.value = emptyList()
    }

    // 🔊 SPEAK (PECS FINAL FEATURE)
    fun speakSentence() {

        if (!voiceEnabled.value) return

        val sentence = getSentence()

        if (sentence.isNotBlank()) {

            viewModelScope.launch {
                historyDao.insert(
                    CommunicationHistory(
                        sentence = sentence
                    )
                )
            }

            tts?.speak(
                sentence,
                TextToSpeech.QUEUE_FLUSH,
                null,
                null
            )
        }
    }

    // 🧠 GET SENTENCE
    fun getSentence(): String {
        val words = _selectedWords.value

        if (words.isEmpty()) return ""

        val sentence = words.joinToString(" ")

        return if (addJeVeux.value) {
            "Je veux $sentence"
        } else {
            sentence
        }
    }

    override fun onCleared() {
        tts?.stop()
        tts?.shutdown()
        super.onCleared()
    }
    fun toggleVoice() {
        voiceEnabled.value = !voiceEnabled.value
    }

    fun toggleJeVeux() {
        addJeVeux.value = !addJeVeux.value
    }

    fun toggleBigIcons() {
        bigIcons.value = !bigIcons.value
    }
}