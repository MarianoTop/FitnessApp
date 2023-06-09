package com.example.fitnessapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.adapters.SemanaAdapter
import com.example.fitnessapp.entities.*
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
        viewModel.semanas.observe(viewLifecycleOwner, Observer { semanas ->
            semanaAdapter = SemanaAdapter(semanas) { position ->
                if (position == -1) {
                    Snackbar.make(v, "Semana no disponible", Snackbar.LENGTH_SHORT).show();
                } else {
                    val posicionRutinaAEnviar =
                        SemanaUtils.obtenerRutinaPorHacer(semanas[position])
                    if(posicionRutinaAEnviar==-1){
                        Snackbar.make(v, "Semana no disponible", Snackbar.LENGTH_SHORT).show();
                    }else if(posicionRutinaAEnviar==-2){
                        Snackbar.make(v, "Usted se ha ausentado", Snackbar.LENGTH_SHORT).show();
                    }else if(posicionRutinaAEnviar==-3){
                        Snackbar.make(v, "Hoy es dia de descanso", Snackbar.LENGTH_SHORT).show();
                    }else if(posicionRutinaAEnviar==-4){
                        Snackbar.make(v, "Ya ha finalizado su rutina de hoy", Snackbar.LENGTH_SHORT).show();

                    }else{
                        val action =
                            EntrenamientoHomeFragmentDirections.actionEntrenamientoHomeFragmentToPrevisualizacionEjercicioFragment(
                                semanas[position].rutinas[posicionRutinaAEnviar], viewModel.usuarioApp.nivelFisico
                            )
                        findNavController().navigate(action)
                    }


                }
            }
            recyclerView.adapter = semanaAdapter
            recyclerView.layoutManager = LinearLayoutManager(context);
        })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EntrenamientoHomeViewModel::class.java)

    }



}