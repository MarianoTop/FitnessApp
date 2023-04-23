package com.example.fitnessapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.fitnessapp.R

class PrevisualizacionEjercicioFragment : Fragment() {

    lateinit var v: View
    lateinit var txtEjercicio1 : TextView
    lateinit var txtEjercicio2 : TextView
    lateinit var txtEjercicio3 : TextView
    lateinit var txtEjercicio4 : TextView
    lateinit var txtCantRepeticiones : TextView

    lateinit var imagenEj1 : ImageView
    lateinit var imagenEj2 : ImageView
    lateinit var imagenEj3 : ImageView
    lateinit var imagenEj4 : ImageView

    lateinit var  btnNavEjercicio : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v= inflater.inflate(R.layout.fragment_previsualizacion_ejercicio, container, false)

        txtEjercicio1= v.findViewById(R.id.lblTxtExercise1)
        txtEjercicio2= v.findViewById(R.id.lblTxtExercise2)
        txtEjercicio3= v.findViewById(R.id.lblTxtExercise3)
        txtEjercicio4= v.findViewById(R.id.lblTxtExercise4)
        txtCantRepeticiones=v.findViewById(R.id.qtyRepetition)

        imagenEj1 =v.findViewById(R.id.imageEjerc1)
        imagenEj2 =v.findViewById(R.id.imageEjerc2)
        imagenEj3 =v.findViewById(R.id.imageEjerc3)
        imagenEj4 =v.findViewById(R.id.imageEjerc4)

        return v
    }

    override fun onStart() {
        super.onStart()
        txtEjercicio1.text= "Testeando1"
        txtEjercicio2.text= "Testeando2"
        txtEjercicio3.text= "Testeando3"
        txtEjercicio4.text= "Testeando4"
        txtCantRepeticiones.text="cantidad de repeticiones: 400"


        Glide
            .with(this)
            .load("https://media.istockphoto.com/id/578104104/es/vector/paso-a-la-instrucci%C3%B3n-en-push-up.jpg?s=612x612&w=0&k=20&c=RNxtdjWZVPjCdk6dBd4wlgNVX7qB6cPFoakeb1Mux8c=")
            .into(imagenEj1);

        Glide
            .with(this)
            .load("https://media.istockphoto.com/id/578104104/es/vector/paso-a-la-instrucci%C3%B3n-en-push-up.jpg?s=612x612&w=0&k=20&c=RNxtdjWZVPjCdk6dBd4wlgNVX7qB6cPFoakeb1Mux8c=")
            .into(imagenEj2);

        Glide
            .with(this)
            .load("https://media.istockphoto.com/id/578104104/es/vector/paso-a-la-instrucci%C3%B3n-en-push-up.jpg?s=612x612&w=0&k=20&c=RNxtdjWZVPjCdk6dBd4wlgNVX7qB6cPFoakeb1Mux8c=")
            .into(imagenEj3);

        Glide
            .with(this)
            .load("https://media.istockphoto.com/id/578104104/es/vector/paso-a-la-instrucci%C3%B3n-en-push-up.jpg?s=612x612&w=0&k=20&c=RNxtdjWZVPjCdk6dBd4wlgNVX7qB6cPFoakeb1Mux8c=")
            .into(imagenEj4);

    }



}