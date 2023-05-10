package com.example.fitnessapp.fragments.NutricionComidas

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.fitnessapp.R

class ComidaEspecificaFragment : Fragment() {

    lateinit var v : View

    private lateinit var viewModel: ComidaEspecificaViewModel

    lateinit var textNombreComida : TextView
    lateinit var imageComida : ImageView
    lateinit var textIngredientes : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_comida_especifica, container, false)

        textNombreComida = v.findViewById(R.id.textNombreComida)
        imageComida = v.findViewById(R.id.imageComida)
        textIngredientes = v.findViewById(R.id.textIngredientes)

        textNombreComida.text = "(Nombre de la comida)" // ${...}
        textIngredientes.text = "Ingredientes"

        // ESTA SE UTILIZARIA EN GENERAL PARA LAS 4 COMIDAS... CON TITULO - IMAGEN - INGREDIENTES

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ComidaEspecificaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}