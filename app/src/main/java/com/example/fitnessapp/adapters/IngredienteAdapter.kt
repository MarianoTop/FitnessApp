package com.example.fitnessapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.entities.Comida

class IngredienteAdapter(var ingredientes : MutableList<String>) : RecyclerView.Adapter<IngredienteAdapter.IngredienteHolder>() {

    class IngredienteHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View

        init {
            this.view = v
        }

        fun setIngredienteText(text: String) {

            val textName: TextView = view.findViewById(R.id.textIngrediente)
            textName.text = text
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredienteHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ingrediente_item, parent, false)
        return (IngredienteHolder(view))
    }

    override fun onBindViewHolder(holder: IngredienteHolder, position: Int) {
        holder.setIngredienteText(ingredientes[position])
    }

    override fun getItemCount(): Int {
        return ingredientes.size
    }
}

