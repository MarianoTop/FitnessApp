package com.example.fitnessapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.entities.Ejercicio
import com.example.fitnessapp.entities.EjercicioRepository
import com.example.fitnessapp.entities.Rutina

class EntrenamientoHomeFragment : Fragment() {

    lateinit var v : View
    lateinit var  btnNavPrev : Button

    private lateinit var viewModel: EntrenamientoHomeViewModel
    var repository: EjercicioRepository= EjercicioRepository()
    var rutina: Rutina= Rutina(1,repository.ejercicios,"piernas",false, false,false)



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_entrenamiento_home, container, false)
        btnNavPrev =v.findViewById(R.id.btnNavPrevisualization)
        return v
    }

    override fun onStart() {
        super.onStart()

        btnNavPrev.setOnClickListener {



            val action =EntrenamientoHomeFragmentDirections.actionEntrenamientoHomeFragmentToPrevisualizacionEjercicioFragment(rutina)
            findNavController().navigate(action)


        }
    }

}