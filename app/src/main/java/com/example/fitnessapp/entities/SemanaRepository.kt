package com.example.fitnessapp.entities

import java.sql.Timestamp
import java.util.Date

class SemanaRepository {

    var semanas: MutableList<Semana>
    var repositoryRutina1: RutinaRepository= RutinaRepository()
    var usuario = Usuario(1,"mariano@gmail.com","Mariano","123", 70.0, 1.70,
    1,24,"Masculino",1,1,Timestamp(Date().time))

    init {
        semanas = mutableListOf()


        //semanas.add(1,repositoryRutina1.rutinas,usuario,false)



    }
}