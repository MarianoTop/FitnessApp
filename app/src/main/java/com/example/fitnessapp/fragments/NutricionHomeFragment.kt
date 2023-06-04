package com.example.fitnessapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R

class NutricionHomeFragment : Fragment() {

    lateinit var v: View

    private val sharedViewModel : NutricionViewModel by activityViewModels()

    lateinit var buttonDesayunos : Button
    lateinit var buttonAlmuerzos : Button
    lateinit var buttonMeriendas : Button
    lateinit var buttonCenas : Button
    lateinit var titulo : String

    var tipo : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_nutricion_home, container, false)

        buttonDesayunos = v.findViewById(R.id.btnDesayunos)
        buttonAlmuerzos = v.findViewById(R.id.btnAlmuerzos)
        buttonMeriendas = v.findViewById(R.id.btnMeriendas)
        buttonCenas     = v.findViewById(R.id.btnCenas)

        buttonDesayunos.text = "Desayuno"
        buttonAlmuerzos.text = "Almuerzo"
        buttonMeriendas.text = "Merienda"
        buttonCenas.text     = "Cena"

        return v
    }

    override fun onStart() {
        super.onStart()

        if (!sharedViewModel.limite) {
            sharedViewModel.listarComidas()
            sharedViewModel.setLimite()
        }

        buttonDesayunos.setOnClickListener {
            titulo = "Desayunos"
            tipo = 0
            val action = NutricionHomeFragmentDirections.actionNutricionHomeFragmentToListaComidasTipoFragment(titulo, tipo)
            findNavController().navigate(action)
        }

        buttonAlmuerzos.setOnClickListener {
            titulo = "Almuerzos"
            tipo = 1
            val action = NutricionHomeFragmentDirections.actionNutricionHomeFragmentToListaComidasTipoFragment(titulo, tipo)
            findNavController().navigate(action)
        }

        buttonMeriendas.setOnClickListener {
            titulo = "Meriendas"
            tipo = 0
            val action = NutricionHomeFragmentDirections.actionNutricionHomeFragmentToListaComidasTipoFragment(titulo, tipo)
            findNavController().navigate(action)
        }

        buttonCenas.setOnClickListener {
            titulo = "Cenas"
            tipo = 1
            val action = NutricionHomeFragmentDirections.actionNutricionHomeFragmentToListaComidasTipoFragment(titulo, tipo)
            findNavController().navigate(action)
        }
    }
}