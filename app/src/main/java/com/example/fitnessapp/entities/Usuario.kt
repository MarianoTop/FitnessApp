package com.example.fitnessapp.entities

import java.sql.Timestamp

data class Usuario(
    var id: String,
    var email: String,
    var nombre: String,
    var contrasenia: String,
    var peso: Double,
    var altura: Double,
    var objetivo: Int, //0 Bajar peso //1 Ganar masa
    var edad:Int,
    var genero:String,
    var reporteSemanal: Int,
    var reporteMensual: Int,
    var fechaCreacionUsuario: Timestamp,
    var nivelFisico: Int, // 0(Bajo), 1(Medio), 2(Alto)
    var diasDeEntrenamiento: MutableList<Boolean>

)
