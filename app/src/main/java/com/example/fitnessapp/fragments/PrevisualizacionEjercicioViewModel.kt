package com.example.fitnessapp.fragments

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.adapters.EjercicioAdapter
import com.example.fitnessapp.entities.EjercicioRepository

class PrevisualizacionEjercicioViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    val cantRepeticiones= MutableLiveData<Int>()

    init{
        changeCantRepeticiones(500)
    }

            fun changeCantRepeticiones(qty:Int){
                cantRepeticiones.value=qty

            }






}