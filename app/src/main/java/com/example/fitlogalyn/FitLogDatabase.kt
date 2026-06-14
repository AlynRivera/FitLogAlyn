package com.example.fitlogalyn

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Ejercicio::class], version = 1)
abstract class FitLogDatabase : RoomDatabase() {

    abstract fun ejercicioDao(): EjercicioDao

    companion object {
        @Volatile
        private var INSTANCE: FitLogDatabase? = null

        fun getDatabase(context: Context): FitLogDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FitLogDatabase::class.java,
                    "fitlog_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}