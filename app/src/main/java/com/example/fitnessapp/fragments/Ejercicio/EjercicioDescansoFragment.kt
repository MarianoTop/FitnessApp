package com.example.fitnessapp.fragments.Ejercicio

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
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.fitnessapp.R
import com.example.fitnessapp.fragments.Ejercicio.EjercicioDescansoFragmentDirections
import kotlinx.coroutines.launch

class EjercicioDescansoFragment : Fragment() {

    lateinit var v : View

    private val sharedViewModel : SharedViewModel by activityViewModels()

    lateinit var textTitle : TextView
    lateinit var textDescanso : TextView
    lateinit var textContador : TextView
    lateinit var textImage : TextView
    lateinit var imageExercise : ImageView
    lateinit var buttonFinDescanso : Button

    var counter = 15
    var counterGlobal : Double = 0.0

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
        buttonFinDescanso = v.findViewById(R.id.btnFinDescanso)

        textDescanso.text = "Descanso"
        textImage.text = "Siguiente ejercicio"
        buttonFinDescanso.text = "Listo"

        return v
    }

    override fun onStart() {
        super.onStart()

        counterGlobal = sharedViewModel.totalTiempo

        duracionEntrenamiento()
        startTimeCounter()

        val posActual = sharedViewModel.posActual
        val ejercicioAnterior = sharedViewModel.ejercAnterior()

        if (posActual.toString() == sharedViewModel.cantEjercicios()) {
            textTitle.text = "Ejercicio terminados"
            textImage.text = "Ultimo ejercicio realizado"

            Glide
                .with(this)
                .load(ejercicioAnterior.image) //
                .into(imageExercise);

            buttonFinDescanso.text = "Finalizar"
        } else {

            textTitle.text = "Ejercicio " + (posActual+1)
            val ejercicioActual = sharedViewModel.ejercActual()

            Glide
                .with(this)
                .load(ejercicioActual.image)
                .into(imageExercise);

        }

        buttonFinDescanso.setOnClickListener {

            if (textContador.text == "0") {
                if (posActual.toString() == sharedViewModel.cantEjercicios()) {
                    sharedViewModel.resetearPos()

                    sharedViewModel.rutinaCompletada()
                    sharedViewModel.guardarTiempoFin()

                    sharedViewModel.viewModelScope.launch{
                        sharedViewModel.persistirRutinaCompletada()
                    }

                    val action =
                        EjercicioDescansoFragmentDirections.actionEjercicioDescansoFragmentToReporteFinEntrenamientoFragment()
                    findNavController().navigate(action)
                } else {
                    val action =
                        EjercicioDescansoFragmentDirections.actionEjercicioDescansoFragmentToEjercicioFragment()
                    findNavController().navigate(action)
                }
            }
        }

    }

    private fun startTimeCounter() {
        object : CountDownTimer(15000,1000) {
        override fun onTick(millisUntilFinished: Long) {
            textContador.text = counter.toString()
            counter--
        }

        override fun onFinish() {
            textContador.text = "0"
        }
        }.start()
    }

    private fun duracionEntrenamiento() {
        object : CountDownTimer(99999999,1000) {
            override fun onTick(millisUntilFinished: Long) {
                sharedViewModel.totalTiempo =+ counterGlobal.toDouble()
                counterGlobal++
            }

            override fun onFinish() {
                sharedViewModel.totalTiempo = counterGlobal.toDouble()
            }
        }.start()
    }

}