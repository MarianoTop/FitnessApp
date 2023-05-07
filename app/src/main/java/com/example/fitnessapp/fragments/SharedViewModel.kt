package com.example.fitnessapp.fragments

import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.fitnessapp.entities.Rutina
import com.example.fitnessapp.entities.Usuario

class SharedViewModel : ViewModel() {

    lateinit var rutina : Rutina
    lateinit var usuario : Usuario

    var totalCalorias : Double = 0.0
    var totalTiempo : Double = 0.0
    var posActual : Int = 0

    fun resetearPos() {
        posActual = 0
    }
    fun incrementarPos() {
        posActual++
    }

    fun cantEjercicios(): String {
        return rutina.ejercicios.size.toString()
    }

}