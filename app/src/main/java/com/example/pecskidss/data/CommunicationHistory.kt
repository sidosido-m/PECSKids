package com.example.pecskidss.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class CommunicationHistory(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val sentence: String,

    val timestamp: Long = System.currentTimeMillis()
)