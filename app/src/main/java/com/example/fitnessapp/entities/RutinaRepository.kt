package com.example.fitnessapp.entities

class RutinaRepository {

    var rutinas:MutableList<Rutina>
    var repositoryEjercicios1: EjercicioRepository= EjercicioRepository()
    var repositoryEjercicios2: EjercicioRepository= EjercicioRepository()
    var repositoryEjercicios3: EjercicioRepository= EjercicioRepository()
    var repositoryEjercicios4: EjercicioRepository= EjercicioRepository()
    var repositoryEjercicios5: EjercicioRepository= EjercicioRepository()
    var repositoryEjercicios6: EjercicioRepository= EjercicioRepository()
    var repositoryEjercicios7: EjercicioRepository= EjercicioRepository()

    init {

        rutinas= mutableListOf( )
        rutinas.add(Rutina("1", repositoryEjercicios1.ejercicios, "Piernas",true,
        true,false,1,1.0)  )
        rutinas.add(Rutina("2",   repositoryEjercicios2.ejercicios, "Brazos",false,
            true,false,1,1.0 ) )
        rutinas.add(Rutina("3",   repositoryEjercicios3.ejercicios, "Pecho",false,
            false,true,1,1.0) )
        rutinas.add(Rutina("4",   repositoryEjercicios4.ejercicios, "Espalda",true,
            false,false,1,1.0) )
        rutinas.add(Rutina("5",   repositoryEjercicios5.ejercicios, "Piernas",false,
            false,false,1,1.0) )
        rutinas.add(Rutina("6",   repositoryEjercicios6.ejercicios, "Gluteos",false,
            false,false,1,1.0) )
        rutinas.add(Rutina("7",   repositoryEjercicios7.ejercicios, "Pecho",false,
            true,false,1,1.0))
    }
}