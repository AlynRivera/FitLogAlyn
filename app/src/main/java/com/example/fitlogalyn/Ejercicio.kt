package com.example.fitlogalyn

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ejercicios")
data class Ejercicio(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val series: Int,
    val repeticiones: Int,
    val peso: Double,
    val fecha: String
)