package com.example.pecskidss.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [PictogramEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PecsDatabase : RoomDatabase() {

    abstract fun pictogramDao(): PictogramDao

    companion object {

        @Volatile
        private var INSTANCE: PecsDatabase? = null

        fun getDatabase(context: Context): PecsDatabase {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PecsDatabase::class.java,
                    "pecs_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}