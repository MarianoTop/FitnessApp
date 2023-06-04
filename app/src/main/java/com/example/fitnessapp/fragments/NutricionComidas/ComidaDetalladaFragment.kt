package com.example.fitnessapp.fragments.NutricionComidas

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitnessapp.R
import com.example.fitnessapp.adapters.ComidaAdapter
import com.example.fitnessapp.adapters.EjercicioAdapter
import com.example.fitnessapp.adapters.IngredienteAdapter
import com.example.fitnessapp.entities.ComidasRepository

class ComidaDetalladaFragment : Fragment() {

    lateinit var v : View

    lateinit var textNombreComida : TextView
    lateinit var imageComida : ImageView
    lateinit var textIngredientes : TextView

    lateinit var recyclerIngredientes : RecyclerView

    lateinit var adapter : IngredienteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_comida_detallada, container, false)

        textNombreComida = v.findViewById(R.id.textNombreComida)
        imageComida = v.findViewById(R.id.imageComida)
        textIngredientes = v.findViewById(R.id.textIngredientes)

        textIngredientes.text = "Ingredientes"

        recyclerIngredientes = v.findViewById(R.id.recyclerIngredientes)

        return v
    }

    override fun onStart() {
        super.onStart()

        val comida = ComidaDetalladaFragmentArgs.fromBundle(requireArguments()).comida

        textNombreComida.text = comida.nombre

        Glide
            .with(this)
            .load(comida.imagen)
            .into(imageComida);

        adapter= IngredienteAdapter(comida.ingredientes)
        recyclerIngredientes.layoutManager = LinearLayoutManager(context)
        recyclerIngredientes.adapter = adapter
    }
}