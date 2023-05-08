package com.example.fitnessapp.fragments

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.fitnessapp.R

class EjercicioComienzaFragment : Fragment() {

    lateinit var v : View

    private val sharedViewModel : SharedViewModel by activityViewModels()

    lateinit var textTitle : TextView
    lateinit var textStart : TextView
    lateinit var textContador : TextView
    lateinit var textImage : TextView
    lateinit var imageExercise : ImageView
    lateinit var buttonComenzar : Button

    var counter = 5

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
        buttonComenzar = v.findViewById(R.id.btnComenzar)

        textStart.text = "Comienza en"
        textImage.text = "Ejercicio a realizar"
        buttonComenzar.text = "Comenzar"
        return v
    }

    override fun onStart() {
        super.onStart()

        startTimeCounter()

        sharedViewModel.asignarRutina(PrevisualizacionEjercicioFragmentArgs.fromBundle(requireArguments()).rutina)

        val posActual = sharedViewModel.posActual
        val ejercicioActual = sharedViewModel.ejercActual()

        textTitle.text = "Ejercicio " + (posActual+1)

        Glide
            .with(this)
            .load(ejercicioActual.image)
            .into(imageExercise);

        buttonComenzar.setOnClickListener {

            if (textContador.text == "0") {

                sharedViewModel.guardarTiempoInicio()

                val action = EjercicioComienzaFragmentDirections.actionEjercicioComienzaFragmentToEjercicioFragment()
                findNavController().navigate(action)
            }
        }

    }

    private fun startTimeCounter() {
        object : CountDownTimer(1000,1000) {
            override fun onTick(millisUntilFinished: Long) {
                textContador.text = counter.toString()
                counter--
            }

            override fun onFinish() {
                textContador.text = "0"
            }
        }.start()
    }


    }
