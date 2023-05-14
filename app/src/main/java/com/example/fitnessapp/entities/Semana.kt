package com.example.fitnessapp.entities

import java.util.Date
import java.sql.Timestamp

data class Semana(
    var id: String = "",
    var rutinas: MutableList<Rutina> = mutableListOf(),
    var rutinasId: MutableList<String> = mutableListOf(),
    var usuario: Usuario = Usuario(),
    var usuarioId: String = "",
    var estaFinalizada: Boolean = false,
    var fechaInicio: Date = Date()
) {
    constructor() : this("", mutableListOf(), mutableListOf(), Usuario(),"", false, Date())
}
