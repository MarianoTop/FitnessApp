package com.example.fitnessapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R

class ReporteFinEntrenamientoFragment : Fragment() {

    lateinit var v : View

    private lateinit var viewModel: ReporteFinEntrenamientoViewModel

    lateinit var imageLogo : ImageView
    lateinit var textTitle : TextView
    lateinit var textDia : TextView
    lateinit var textInfo1 : TextView
    lateinit var textInfo2 : TextView
    lateinit var textCalorias : TextView
    lateinit var textDuracion : TextView
    lateinit var btnTerminar : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_reporte_fin_entrenamiento, container, false)

        imageLogo = v.findViewById(R.id.imageLogo)
        textTitle = v.findViewById(R.id.textTitle)
        textDia = v.findViewById(R.id.textDia)
        textInfo1 = v.findViewById(R.id.textInfo1)
        textInfo2 = v.findViewById(R.id.textInfo2)
        textCalorias = v.findViewById(R.id.textNroCal)
        textDuracion = v.findViewById(R.id.textDuracion)
        btnTerminar = v.findViewById(R.id.buttonEnd)

        textTitle.text = "Entrenamiento Finalizado"
        textInfo1.text = "Calorias Quemadas"
        textInfo2.text = "Duracion"
        btnTerminar.text = "Terminar"

        return v
    }

    override fun onStart() {
        super.onStart()

        btnTerminar.setOnClickListener(){
            // val action = ReporteFinEntrenamientoFragmentDirections.action...()
            // findNavController().navigate(action)
        }
    }

}