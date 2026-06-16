package com.example.fitlogalyn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class HistorialFragment : Fragment() {

    private lateinit var db: FitLogDatabase
    private lateinit var adapter: EjercicioAdapter
    private lateinit var tvTotal: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_historial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = FitLogDatabase.getDatabase(requireContext())
        tvTotal = view.findViewById(R.id.tvTotalEjercicios)

        adapter = EjercicioAdapter(emptyList()) { ejercicio ->
            viewLifecycleOwner.lifecycleScope.launch {
                db.ejercicioDao().eliminar(ejercicio)
            }
        }

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerHistorial)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        db.ejercicioDao().obtenerTodos().observe(viewLifecycleOwner) { lista ->
            adapter.actualizarLista(lista)
            tvTotal.text = "Total de ejercicios registrados: ${lista.size}"
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }
}
