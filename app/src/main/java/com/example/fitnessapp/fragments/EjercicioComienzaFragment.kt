package com.example.fitnessapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.fitnessapp.R

class EjercicioComienzaFragment : Fragment() {

    lateinit var v : View

    private lateinit var viewModel: EjercicioComienzaFragmentViewModel

    lateinit var textTitle : TextView
    lateinit var textStart : TextView
    lateinit var textContador : TextView // VER QUE ONDA...
    lateinit var textImage : TextView
    lateinit var imageExercise : ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_ejercicio_comienza, container, false)

        textTitle = v.findViewById(R.id.textTitle)
        textStart = v.findViewById(R.id.textStart)
        textContador = v.findViewById(R.id.textContador)
        textImage = v.findViewById(R.id.textImage)
        imageExercise = v.findViewById(R.id.imageExercise)

        textTitle.text = "Ejercicio 01/00"
        textStart.text = "Comienza en"
        textContador.text = "00:05"
        textImage.text = "Ejercicio a realizar"

        return v
    }

}