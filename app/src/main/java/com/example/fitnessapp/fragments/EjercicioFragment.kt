package com.example.fitnessapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.fitnessapp.R

class EjercicioFragment : Fragment() {

    lateinit var v : View

    private lateinit var viewModel: EjercicioViewModel

    lateinit var textTitle : TextView
    lateinit var textNameExercise : TextView
    lateinit var textContadorOrRep : TextView // VER QUE ONDA...
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

        textTitle.text = "Ejercicio 01/00"
        textNameExercise.text = "Nombre Ejercicio"
        textContadorOrRep.text = "X10" // VER COMO USAR ESTO...
        btnNext.text = "Listo"

        return v
    }

    override fun onStart() {
        super.onStart()

        btnNext.setOnClickListener {
            // val action = Screen1FragmentDirections.actionScreen1FragmentToMainNavgraph()
            // findNavController().navigate(action)
            // NO FUNCIONA - ES COPY-PASTE
        }

    }
}