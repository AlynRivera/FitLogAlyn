package com.example.fitlogalyn

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.lifecycleScope
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var db: FitLogDatabase
    private lateinit var adapter: EjercicioAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = FitLogDatabase.getDatabase(this)

        adapter = EjercicioAdapter(emptyList()) { ejercicio ->
            lifecycleScope.launch {
                db.ejercicioDao().eliminar(ejercicio)
            }
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        db.ejercicioDao().obtenerTodos().observe(this, Observer { lista ->
            adapter.actualizarLista(lista)
        })

        findViewById<FloatingActionButton>(R.id.fabAgregar).setOnClickListener {
            startActivity(Intent(this, AgregarEjercicioActivity::class.java))
        }
        findViewById<Button>(R.id.btnHistorial).setOnClickListener {
            startActivity(Intent(this, HistorialActivity::class.java))
        }
    }
}