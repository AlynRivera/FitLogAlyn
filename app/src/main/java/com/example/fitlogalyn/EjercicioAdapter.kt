package com.example.fitlogalyn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EjercicioAdapter(
    private var ejercicios: List<Ejercicio>,
    private val onEliminar: (Ejercicio) -> Unit
) : RecyclerView.Adapter<EjercicioAdapter.EjercicioViewHolder>() {

    class EjercicioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvDetalles: TextView = itemView.findViewById(R.id.tvDetalles)
        val tvFecha: TextView = itemView.findViewById(R.id.tvFecha)
        val btnEliminar: ImageButton = itemView.findViewById(R.id.btnEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EjercicioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ejercicio, parent, false)
        return EjercicioViewHolder(view)
    }

    override fun onBindViewHolder(holder: EjercicioViewHolder, position: Int) {
        val ejercicio = ejercicios[position]
        holder.tvNombre.text = ejercicio.nombre
        holder.tvDetalles.text = "${ejercicio.series} series × ${ejercicio.repeticiones} reps — ${ejercicio.peso} kg"
        holder.tvFecha.text = ejercicio.fecha
        holder.btnEliminar.setOnClickListener { onEliminar(ejercicio) }
    }

    override fun getItemCount() = ejercicios.size

    fun actualizarLista(nuevaLista: List<Ejercicio>) {
        ejercicios = nuevaLista
        notifyDataSetChanged()
    }
}