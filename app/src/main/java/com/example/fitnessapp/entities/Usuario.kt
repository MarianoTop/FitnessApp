package com.example.fitnessapp.entities

import java.sql.Timestamp

data class Usuario(
    var id: Long,
    var nombre: String,
    var contraseña: String,
    var peso: Double,
    var altura: Double,
    var objetivo: Int,
    var edad:Int,
    var genero:String,
    var reporteSemanal: Int,
    var reporteMensual: Int,
    var hora: String,
    var diasDeEntrenamiento: MutableList<Boolean>


)
