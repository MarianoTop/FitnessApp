package com.example.fitnessapp.entities

data class Ejercicio(
    var id: Int,
    var image: String,
    var description :String,
    var cantidad: Int,
    var paraBajarPeso: Int,
    var paraGanarPeso: Int,
    var grupoMuscular: String,
    var subGrupoMuscular: String
)
