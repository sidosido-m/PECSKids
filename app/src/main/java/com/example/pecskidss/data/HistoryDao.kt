package com.example.pecskidss.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Insert
    suspend fun insert(history: CommunicationHistory)

    @Query("SELECT * FROM history ORDER BY timestamp DESC")
    fun getAllHistory(): Flow<List<CommunicationHistory>>
    @Query("DELETE FROM history")
    suspend fun deleteAll()
}