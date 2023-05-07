package com.example.fitnessapp.entities

import java.sql.Timestamp
import java.util.*

class SemanaRepository {

    var semanas: MutableList<Semana>
    var repositoryRutina1: RutinaRepository= RutinaRepository()
    var repositoryRutina2: RutinaRepository= RutinaRepository()
    var usuario = Usuario(1,"mariano@gmail.com","Mariano","123", 70.0, 1.70,
    1,24,"Masculino",1,1,Timestamp(Date().time))

    init {
        semanas = mutableListOf()

        val c = Calendar.getInstance()
        c.set(Calendar.YEAR,2023)
        c.set(Calendar.MONTH, Calendar.MAY)
        c.set(Calendar.DAY_OF_MONTH,1)

       var dateAPasar = c.time


        semanas.add(Semana(1,repositoryRutina1.rutinas,usuario,false, Timestamp(dateAPasar.time)))

        val d = Calendar.getInstance()
        d.set(Calendar.YEAR,2023)
        d.set(Calendar.MONTH, Calendar.MAY)
        d.set(Calendar.DAY_OF_MONTH,1)

        var dateAPasar2 = d.time

        semanas.add(Semana(2,repositoryRutina1.rutinas,usuario,false, Timestamp(dateAPasar2.time)))


    }
}