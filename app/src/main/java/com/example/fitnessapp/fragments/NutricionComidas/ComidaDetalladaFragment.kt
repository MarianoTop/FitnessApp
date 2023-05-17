package com.example.fitnessapp.fragments.NutricionComidas

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.fitnessapp.R

class ComidaDetalladaFragment : Fragment() {

    lateinit var v : View

    private lateinit var viewModel: ComidaDetalladaViewModel

    lateinit var textNombreComida : TextView
    lateinit var imageComida : ImageView
    lateinit var textIngredientes : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_comida_detallada, container, false)

        textNombreComida = v.findViewById(R.id.textNombreComida)
        imageComida = v.findViewById(R.id.imageComida)
        textIngredientes = v.findViewById(R.id.textIngredientes)

        textIngredientes.text = "Ingredientes"  // PARA LOS INGREDIENTES CREO QUE USAR UN RECYCLERVIEW SERIA BUENA IDEA

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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ComidaDetalladaViewModel::class.java)
    }

}