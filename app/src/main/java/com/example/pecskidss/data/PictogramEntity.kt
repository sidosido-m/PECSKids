package com.example.pecskidss.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pictograms")
data class PictogramEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,

    val category: String,

    // الأفضل للمستقبل: نخزن اسم الصورة أو URI وليس فقط res
    val imageRes: Int
)