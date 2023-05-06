package com.example.fitnessapp.fragments

import androidx.lifecycle.ViewModel
import com.example.fitnessapp.entities.Rutina
import com.example.fitnessapp.entities.Usuario

class SharedViewModel : ViewModel() {

    var totalCalorias : Double = 0.0
    var totalTiempo : Double = 0.0
    lateinit var rutina : Rutina
    lateinit var usuario : Usuario

}