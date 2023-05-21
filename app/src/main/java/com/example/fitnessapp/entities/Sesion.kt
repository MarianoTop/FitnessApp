package com.example.fitnessapp.entities

data class Sesion(

    var id: String?,
    var usuario:Usuario,
    var idUsuario : String,
    var idRutina : String,
    var rutina:Rutina,
    //a chequear si conviene que sea un string o un timestamp o un double
    var duracionSesion: String,
    var caloriasQuemadas: Double

) {
      constructor(idUsuario: String, idRutina: String, duracionSesion: String, caloriasQuemadas: Double) : this(
          null,
          Usuario(),
        idUsuario,
        idRutina,
        Rutina(),
          duracionSesion,
          caloriasQuemadas,
    )
}
