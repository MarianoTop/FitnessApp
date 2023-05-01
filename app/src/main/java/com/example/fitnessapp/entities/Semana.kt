package com.example.fitnessapp.entities

data class Semana(
    var id: Long,
    var rutinas: MutableList<Rutina>,
    var usuario: Usuario
)
