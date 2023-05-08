package com.example.fitnessapp.entities

import java.sql.Timestamp

data class Semana(
    var id: Long,
    var rutinas: MutableList<Rutina>,
    var usuario: Usuario,
    var estaFinalizada: Boolean,
    var fechaInicio: Timestamp
)
