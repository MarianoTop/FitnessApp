package com.example.fitnessapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.adapters.SemanaAdapter
import com.example.fitnessapp.entities.Ejercicio
import com.example.fitnessapp.entities.EjercicioRepository
import com.example.fitnessapp.entities.Rutina
import com.example.fitnessapp.entities.SemanaRepository
import com.example.fitnessapp.utils.SemanaUtils
import com.google.android.material.snackbar.Snackbar

class EntrenamientoHomeFragment : Fragment() {

    lateinit var v : View
    //lateinit var  btnStartExcercise : Button

    private lateinit var viewModel: EntrenamientoHomeViewModel
    //var repository: EjercicioRepository= EjercicioRepository()
    var repositorySemanas: SemanaRepository=SemanaRepository()
    //var rutina: Rutina= Rutina(1,repository.ejercicios,"piernas",false, false,false)
    lateinit var semanaAdapter : SemanaAdapter;
    lateinit var recyclerView: RecyclerView;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_entrenamiento_home, container, false)
        recyclerView = v.findViewById(R.id.recyclerViewSemana)
        //btnStartExcercise =v.findViewById(R.id.btn)
        return v
    }

    override fun onStart() {
        super.onStart()

        semanaAdapter = SemanaAdapter(repositorySemanas.semanas){position ->


            if(position == -1){
                Snackbar.make(v, "Semana no disponible", Snackbar.LENGTH_SHORT).show();
            }else {
                var posicionRutinaAEnviar =
                    SemanaUtils.obtenerRutinaPorHacer(repositorySemanas.semanas[position])
                println(posicionRutinaAEnviar)
                val action =
                    EntrenamientoHomeFragmentDirections.actionEntrenamientoHomeFragmentToPrevisualizacionEjercicioFragment(
                        repositorySemanas.semanas[position].rutinas[posicionRutinaAEnviar]
                    )
                findNavController().navigate(action)
            }
        };
        recyclerView.layoutManager = LinearLayoutManager(context);
        recyclerView.adapter = semanaAdapter;

        /*
        btnStartExcercise.setOnClickListener {
            val action =EntrenamientoHomeFragmentDirections.actionEntrenamientoHomeFragmentToPrevisualizacionEjercicioFragment(rutina)
            findNavController().navigate(action)


        }

         */
    }

}