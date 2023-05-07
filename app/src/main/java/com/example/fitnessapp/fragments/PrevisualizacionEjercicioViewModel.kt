package com.example.fitnessapp.fragments

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.adapters.EjercicioAdapter
import com.example.fitnessapp.entities.Ejercicio
import com.example.fitnessapp.entities.EjercicioRepository
import com.example.fitnessapp.entities.Rutina

class PrevisualizacionEjercicioViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    val cantRepeticiones= MutableLiveData<Int>()
    private lateinit  var ejercicios: MutableList<Ejercicio>
    private lateinit var rutinaAPasar: Rutina

    init{
        changeCantRepeticiones(500)
    }
    fun changeCantRepeticiones(qty:Int){
                cantRepeticiones.value=qty
            }
    fun setEjercicios (ejercicios: MutableList<Ejercicio>){
        this.ejercicios=ejercicios
    }

    fun getRutinaAPasar (): Rutina{
        return rutinaAPasar
    }

    fun setRutinaAPasar (rutinaAPasar: Rutina){
        this.rutinaAPasar=rutinaAPasar
    }

    fun getEjercicios (): MutableList<Ejercicio>{
        return ejercicios
    }


}