package com.example.fitnessapp.entities

import java.sql.Timestamp
import java.util.*

class SemanaRepository {

    var semanas: MutableList<Semana>
    var repositoryRutina1: RutinaRepository= RutinaRepository()
    var repositoryRutina2: RutinaRepository= RutinaRepository()

    var mytime= Timestamp(Date().time)
    var diasDeEntrenamiento =mutableListOf(true,false,false,true,true,false,true)
    var usuario : Usuario = Usuario("1", "test@gmail.com", "test", "test", 90.0, 180.0, 80, 21, "Masculino", 1, 0,mytime,
        0,diasDeEntrenamiento)


    init {
        semanas = mutableListOf()

        val c = Calendar.getInstance()
        c.set(Calendar.YEAR,2023)
        c.set(Calendar.MONTH, Calendar.APRIL)
        c.set(Calendar.DAY_OF_MONTH,24)

       var dateAPasar = c.time


        semanas.add(Semana("1",repositoryRutina1.rutinas, mutableListOf(), usuario, "",true, Timestamp(dateAPasar.time)))

        val d = Calendar.getInstance()
        d.set(Calendar.YEAR,2023)
        d.set(Calendar.MONTH, Calendar.MAY)
        d.set(Calendar.DAY_OF_MONTH,1)

        var dateAPasar2 = d.time

        semanas.add(Semana("2",repositoryRutina2.rutinas,mutableListOf(), usuario,"",false, Timestamp(dateAPasar2.time)))


    }
}