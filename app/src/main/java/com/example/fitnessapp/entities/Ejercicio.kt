package com.example.fitnessapp.entities

data class Ejercicio(
    var id: String,
    var image: String,
    var description :String,
    var cantidad: Int,
    var bajarPeso: Boolean,
    var ganarMasa: Boolean,
    var grupoMuscular: String,
    var subGrupoMusc: String
) {
    constructor(): this("", "", "", 0,false,false,"","")
}
