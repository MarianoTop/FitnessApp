package com.example.fitnessapp.entities

import java.sql.Timestamp
import java.util.*

data class Usuario(
    var id: String = "",
    var email: String = "",
    var nombre: String = "",
    var contrasenia: String = "",
    var peso: Double = 0.0,
    var altura: Double = 0.0,
    var objetivo: Int = 0,
    var edad: Int = 0,
    var genero: String = "",
    var reporteSemanal: Int = 0,
    var reporteMensual: Int = 0,
    var fechaCreacionUsuario: Date = Date(),
    var nivelFisico: Int = 0,
    var diasDeEntrenamiento: MutableList<Boolean> = mutableListOf()
) {
    constructor() : this("", "", "", "", 0.0, 0.0, 0, 0, "", 0, 0, Timestamp(System.currentTimeMillis()), 0, mutableListOf())
}
