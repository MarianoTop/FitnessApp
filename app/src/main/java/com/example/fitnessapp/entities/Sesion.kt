package com.example.fitnessapp.entities

data class Sesion(

    var id: String, // Firebase genera un ID al momento de agregar a la BD
    var usuario:Usuario,
    var rutina:Rutina,
    //a chequear si conviene que sea un string o un timestamp o un double
    var duracionSesion: String,
    var caloriasQuemadas: Double

)
