package com.example.fitnessapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.activities.MainActivity2
import com.example.fitnessapp.adapters.EjercicioAdapter
import com.example.fitnessapp.entities.EjercicioRepository

class PrevisualizacionEjercicioFragment : Fragment() {

    lateinit var v: View
    var repository: EjercicioRepository = EjercicioRepository()
    lateinit var recyclerEjercicios : RecyclerView
    lateinit var adapter: EjercicioAdapter

    /*
    lateinit var txtEjercicio1 : TextView
    lateinit var txtEjercicio2 : TextView
    lateinit var txtEjercicio3 : TextView
    lateinit var txtEjercicio4 : TextView


    lateinit var imagenEj1 : ImageView
    lateinit var imagenEj2 : ImageView
    lateinit var imagenEj3 : ImageView
    lateinit var imagenEj4 : ImageView
*/

    lateinit var txtCantRepeticiones : TextView
    lateinit var  btnNavEjercicio : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v= inflater.inflate(R.layout.fragment_previsualizacion_ejercicio, container, false)

        /*
        txtEjercicio1= v.findViewById(R.id.lblTxtExercise1)
        txtEjercicio2= v.findViewById(R.id.lblTxtExercise2)
        txtEjercicio3= v.findViewById(R.id.lblTxtExercise3)
        txtEjercicio4= v.findViewById(R.id.lblTxtExercise4)


        imagenEj1 =v.findViewById(R.id.imageEjerc1)
        imagenEj2 =v.findViewById(R.id.imageEjerc2)
        imagenEj3 =v.findViewById(R.id.imageEjerc3)
        imagenEj4 =v.findViewById(R.id.imageEjerc4)
*/
        recyclerEjercicios=v.findViewById(R.id.recyclerEjercicios)

        txtCantRepeticiones=v.findViewById(R.id.qtyRepetition)
        return v
    }

    override fun onStart() {
        super.onStart()

        txtCantRepeticiones.text="cantidad de repeticiones: 400"

        lateinit var contextSent :Context

         if(context!=null){
             contextSent= context as Context
         }



        adapter= EjercicioAdapter(contextSent,repository.ejercicios)
        recyclerEjercicios.layoutManager=LinearLayoutManager(context)
        recyclerEjercicios.adapter=adapter
      /*
        txtEjercicio1.text= "Testeando1"
        txtEjercicio2.text= "Testeando2"
        txtEjercicio3.text= "Testeando3"
        txtEjercicio4.text= "Testeando4"



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
*/
    }



}