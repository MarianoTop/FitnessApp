package com.example.fitnessapp.entities

data class Rutina(
    var id: Int,
    var ejercicios: MutableList<Ejercicio>,
    var grupoMuscular: String
)
