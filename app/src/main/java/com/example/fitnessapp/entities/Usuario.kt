package com.example.fitnessapp.entities

import java.sql.Timestamp

data class Usuario(
    var id: Long,
    var email : String,
    var nombre: String,
    var contraseña: String,
    var peso: Double,
    var altura: Double,
    var objetivo: Int,
    var edad:Int,
    var genero:String,
    var reporteSemanal: Int,
    var reporteMensual: Int,
    var hora: Timestamp,
    //var nivelFisico: MutableList<Boolean>, // 0(Bajo), 1(Medio), 2(Alto)
    //var diasDeEntrenamiento: MutableList<Boolean>


)
