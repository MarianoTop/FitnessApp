package com.example.fitnessapp.entities

data class Rutina(
    var id: Long,
    var ejercicios: MutableList<Ejercicio>,
    var grupoMuscular: String
)
