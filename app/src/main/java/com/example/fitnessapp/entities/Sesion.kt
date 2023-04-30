package com.example.fitnessapp.entities

data class Sesion(

    var id: Long,
    var usuario:Usuario,
    var rutina:Rutina,
    //a chequear si conviene que sea un string o un timestamp o un double
    var duracionSesion: String,
    var caloriasQuemadas: Double

)
