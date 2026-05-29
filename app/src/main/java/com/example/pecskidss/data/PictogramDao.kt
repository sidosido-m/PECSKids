package com.example.pecskidss.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PictogramDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pictogram: PictogramEntity)

    @Delete
    suspend fun delete(pictogram: PictogramEntity)

    @Update
    suspend fun update(pictogram: PictogramEntity)

    @Query("SELECT * FROM pictograms ORDER BY name ASC")
    fun getAllPictograms(): Flow<List<PictogramEntity>>

    @Query("SELECT * FROM pictograms WHERE category = :category")
    fun getByCategory(category: String): Flow<List<PictogramEntity>>

    @Query("SELECT * FROM pictograms WHERE name = :name LIMIT 1")
    suspend fun getByName(name: String): PictogramEntity?

    @Query("SELECT COUNT(*) FROM pictograms")
    suspend fun count(): Int

    @Query("SELECT name FROM pictograms")
    suspend fun getAllPictogramNames(): List<String>
}