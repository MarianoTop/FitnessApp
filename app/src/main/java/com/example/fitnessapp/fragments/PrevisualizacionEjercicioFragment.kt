package com.example.fitnessapp.fragments

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.adapters.EjercicioAdapter
import com.example.fitnessapp.entities.EjercicioRepository


class PrevisualizacionEjercicioFragment : Fragment() {

    lateinit var v: View
    var repository: EjercicioRepository = EjercicioRepository()
    lateinit var recyclerEjercicios : RecyclerView
    lateinit var adapter: EjercicioAdapter


    lateinit var txtCantRepeticiones : TextView
    lateinit var  btnNavEjercicio : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v= inflater.inflate(R.layout.fragment_previsualizacion_ejercicio, container, false)

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

     /*   val decoration = DividerItemDecoration(context,LinearLayoutManager(context).orientation)
        decoration.setDrawable(ColorDrawable( resources.getColor(R.color.white)));
        recyclerEjercicios.addItemDecoration(decoration)*/



    }



}