package com.example.fitnessapp.entities

data class Semana(
    var id: Int,
    var rutinas: MutableList<Rutina>,
    var usuario: Usuario
)
