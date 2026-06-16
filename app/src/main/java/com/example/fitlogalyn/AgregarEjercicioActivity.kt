package com.example.fitlogalyn

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AgregarEjercicioActivity : AppCompatActivity() {

    private lateinit var db: FitLogDatabase
    private lateinit var etNombre: TextInputEditText
    private lateinit var etSeries: TextInputEditText
    private lateinit var etRepeticiones: TextInputEditText
    private lateinit var etPeso: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar)

        db = FitLogDatabase.getDatabase(this)

        etNombre = findViewById(R.id.etNombre)
        etSeries = findViewById(R.id.etSeries)
        etRepeticiones = findViewById(R.id.etRepeticiones)
        etPeso = findViewById(R.id.etPeso)

        findViewById<Button>(R.id.btnGuardar).setOnClickListener {
            guardarEjercicio()
        }
    }

    private fun guardarEjercicio() {
        val nombre = etNombre.text.toString().trim()
        val series = etSeries.text.toString().trim()
        val repeticiones = etRepeticiones.text.toString().trim()
        val peso = etPeso.text.toString().trim()

        if (nombre.isEmpty() || series.isEmpty() || repeticiones.isEmpty() || peso.isEmpty()) {
            Toast.makeText(this, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val fecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

        val ejercicio = Ejercicio(
            nombre = nombre,
            series = series.toInt(),
            repeticiones = repeticiones.toInt(),
            peso = peso.toDouble(),
            fecha = fecha
        )

        lifecycleScope.launch {
            db.ejercicioDao().insertar(ejercicio)
            runOnUiThread {
                Toast.makeText(this@AgregarEjercicioActivity, "¡Ejercicio guardado!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
