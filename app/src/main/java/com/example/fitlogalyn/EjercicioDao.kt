package com.example.fitlogalyn

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EjercicioDao {

    @Insert
    suspend fun insertar(ejercicio: Ejercicio)

    @Update
    suspend fun actualizar(ejercicio: Ejercicio)

    @Delete
    suspend fun eliminar(ejercicio: Ejercicio)

    @Query("SELECT * FROM ejercicios ORDER BY fecha DESC")
    fun obtenerTodos(): LiveData<List<Ejercicio>>
}