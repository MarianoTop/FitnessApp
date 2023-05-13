package com.example.fitnessapp.fragments

import android.content.ContentValues
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
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
import com.example.fitnessapp.entities.*
import com.example.fitnessapp.utils.SemanaUtils
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import java.util.*

class EntrenamientoHomeFragment : Fragment() {

    lateinit var v : View
    //lateinit var  btnStartExcercise : Button

    private lateinit var viewModel: EntrenamientoHomeViewModel
    //var repository: EjercicioRepository= EjercicioRepository()
    var repositorySemanas: SemanaRepository=SemanaRepository()
    //var rutina: Rutina= Rutina(1,repository.ejercicios,"piernas",false, false,false)
    lateinit var semanaAdapter : SemanaAdapter;
    lateinit var recyclerView: RecyclerView;
    val db = Firebase.firestore


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
        val manager = ManagerRutinas()
        //var semanaCreada = false

        val semanas : MutableList<Semana> = arrayListOf()
        db.collection("semanas").get().addOnSuccessListener {snapshot ->
            if(snapshot != null) {
                for(semana in snapshot) {
                    val semanaObj : Semana = semana.toObject()
                    db.collection("usuarios").whereEqualTo("id", semanaObj.usuarioId).get().addOnSuccessListener {usuarioBase ->
                        if(usuarioBase != null) {
                            for(usuario in usuarioBase) {
                                semanaObj.usuario = usuario.toObject()
                            }
                            //if(!semanaCreada)
                            //{
                                //manager.crearSemana(semanaObj.usuario,  Date(20230515))
                                //semanaCreada = true
                            //}
                        }
                    }.addOnFailureListener { exception ->
                        Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                    }
                    db.collection("rutinas").whereIn("id", semanaObj.rutinasId).get().addOnSuccessListener {rutinasObtenidas ->
                        if(rutinasObtenidas != null) {
                            for(rutina in rutinasObtenidas) {
                                val rutinaObj : Rutina = rutina.toObject()
                                db.collection("ejercicios").whereIn("id", rutinaObj.ejerciciosId).get().addOnSuccessListener {ejerciciosObtenidos ->
                                    if(ejerciciosObtenidos != null) {
                                        for(ejercicio in ejerciciosObtenidos) {
                                            val ejercicioObj : Ejercicio = ejercicio.toObject()
                                            ejercicioObj.cantidad = manager.calcularCantidadDeRepeticiones(ejercicioObj, semanaObj.usuario)
                                            rutinaObj.ejercicios.add(ejercicioObj)
                                            println(rutina)
                                        }
                                    }
                                }.addOnFailureListener { exception ->
                                    Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                                }
                                semanaObj.rutinas.add(rutinaObj)
                                println(rutina)
                            }
                        }
                    }.addOnFailureListener { exception ->
                        Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                    }

                    semanas.add(semanaObj)
                }
                semanaAdapter = SemanaAdapter(semanas) { position ->
                    //Snackbar.make(v, "Click en ${repository.ejercicios[position].description}", Snackbar.LENGTH_SHORT).show();

                    val posicionRutinaAEnviar = SemanaUtils.obtenerRutinaPorHacer(semanas[position])
                    println("posicion enviada: " + posicionRutinaAEnviar)
                    val action =EntrenamientoHomeFragmentDirections.actionEntrenamientoHomeFragmentToPrevisualizacionEjercicioFragment(semanas[position].rutinas[posicionRutinaAEnviar])
                    findNavController().navigate(action)
                };
                recyclerView.layoutManager = LinearLayoutManager(context);
                recyclerView.adapter = semanaAdapter;

            }
        }.addOnFailureListener { exception ->
            Log.w(ContentValues.TAG, "Error getting documents: ", exception)
        }



        /*
        btnStartExcercise.setOnClickListener {
            val action =EntrenamientoHomeFragmentDirections.actionEntrenamientoHomeFragmentToPrevisualizacionEjercicioFragment(rutina)
            findNavController().navigate(action)


        }

         */
    }

}