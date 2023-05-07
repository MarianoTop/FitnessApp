package com.example.fitnessapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.fitnessapp.R

class EjercicioFragment : Fragment() {

    lateinit var v : View

    private val sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

    lateinit var textTitle : TextView
    lateinit var textNameExercise : TextView
    lateinit var textContadorOrRep : TextView // REVISAR...
    lateinit var imageExercise : ImageView
    lateinit var btnNext : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_ejercicio, container, false)

        textTitle = v.findViewById(R.id.textTitle1)
        textNameExercise = v.findViewById(R.id.textNameExercise)
        textContadorOrRep = v.findViewById(R.id.textContadorOrRep)
        imageExercise = v.findViewById(R.id.imageExercise1)
        btnNext = v.findViewById(R.id.btnNext)

        btnNext.text = "Listo"

        return v
    }

    override fun onStart() {
        super.onStart()

        val posActual = sharedViewModel.posActual
        val ejercicioActual = sharedViewModel.rutina.ejercicios[posActual]

        textTitle.text = "Ejercicio " + {posActual+1}
        textNameExercise.text = ejercicioActual.description
        textContadorOrRep.text = "X" + ejercicioActual.cantidad.toString()

        Glide
            .with(this)
            .load(ejercicioActual.image)
            .into(imageExercise);


        btnNext.setOnClickListener {

            sharedViewModel.incrementarPos()

             val action = EjercicioFragmentDirections.actionEjercicioFragmentToEjercicioDescansoFragment()
             findNavController().navigate(action)
        }

    }

}
