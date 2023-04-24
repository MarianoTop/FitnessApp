package com.example.fitnessapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.fitnessapp.R

class EjercicioDescansoFragment : Fragment() {

    lateinit var v : View

    private lateinit var viewModel: EjercicioDescansoViewModel

    lateinit var textTitle : TextView
    lateinit var textDescanso : TextView
    lateinit var textContador : TextView // VER QUE ONDA...
    lateinit var textImage : TextView
    lateinit var imageExercise : ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.fragment_ejercicio_descanso, container, false)

        textTitle = v.findViewById(R.id.textTitle2)
        textDescanso = v.findViewById(R.id.textDescanso)
        textContador = v.findViewById(R.id.textContador1)
        textImage = v.findViewById(R.id.textImage1)
        imageExercise = v.findViewById(R.id.imageExercise2)

        textTitle.text = "Ejercicio 02/00"
        textDescanso.text = "Descanso"
        textContador.text = "00:15"
        textImage.text = "Siguiente ejercicio"

        return v
    }

}