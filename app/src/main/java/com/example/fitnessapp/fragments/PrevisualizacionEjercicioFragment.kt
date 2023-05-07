package com.example.fitnessapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.adapters.EjercicioAdapter


class PrevisualizacionEjercicioFragment : Fragment() {

    lateinit var v: View

    lateinit var recyclerEjercicios : RecyclerView
    lateinit var adapter: EjercicioAdapter


    lateinit var txtCantRepeticiones : TextView
    lateinit var  btnNavEjercicio : Button

    private lateinit var viewModel: PrevisualizacionEjercicioViewModel
    companion object {
        fun newInstance() = PrevisualizacionEjercicioFragment()
    }

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
        viewModel.cantRepeticiones.observe(viewLifecycleOwner, Observer { result ->
            txtCantRepeticiones.text= "cantidad de repeticiones: ${result.toString()}"
        })

        val rutina = PrevisualizacionEjercicioFragmentArgs.fromBundle(requireArguments()).rutina

        viewModel.setRutinaAPasar(rutina)
        viewModel.setEjercicios(rutina.ejercicios)


        lateinit var contextSent :Context
         if(context!=null){
             contextSent= context as Context
         }
        adapter= EjercicioAdapter(contextSent,viewModel.getEjercicios())
        recyclerEjercicios.layoutManager=LinearLayoutManager(context)
        recyclerEjercicios.adapter=adapter

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PrevisualizacionEjercicioViewModel::class.java)
        // TODO: Use the ViewModel
    }



}