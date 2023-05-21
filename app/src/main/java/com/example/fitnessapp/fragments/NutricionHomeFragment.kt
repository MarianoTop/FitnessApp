package com.example.fitnessapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R

class NutricionHomeFragment : Fragment() {

    lateinit var v: View

    private lateinit var viewModel: NutricionHomeViewModel

    lateinit var buttonDesayunos : Button
    lateinit var buttonAlmuerzos : Button
    lateinit var buttonMeriendas : Button
    lateinit var buttonCenas : Button

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

        buttonDesayunos.setOnClickListener {
                val action = NutricionHomeFragmentDirections.actionNutricionHomeFragmentToDesayunosFragment()
                findNavController().navigate(action)
        }

        buttonAlmuerzos.setOnClickListener {
                val action = NutricionHomeFragmentDirections.actionNutricionHomeFragmentToAlmuerzosFragment()
                findNavController().navigate(action)
        }

        buttonMeriendas.setOnClickListener {
                val action = NutricionHomeFragmentDirections.actionNutricionHomeFragmentToMeriendasFragment()
                findNavController().navigate(action)
        }

        buttonCenas.setOnClickListener {
                val action = NutricionHomeFragmentDirections.actionNutricionHomeFragmentToCenasFragment()
                findNavController().navigate(action)
        }


    }
}