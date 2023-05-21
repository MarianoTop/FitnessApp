package com.example.fitnessapp.entities

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.random.Random

class ManagerRutinas {

    val db: DbRepository = DbRepository()

    fun crearSemana(usuario: Usuario) {
        val gruposMusculares: MutableList<String> = mutableListOf()
        gruposMusculares.add("Piernas")
        gruposMusculares.add("Brazos")
        gruposMusculares.add("Abdomen")

        for(dia in usuario.diasDeEntrenamiento)
        {
            if(dia)
            {
                crearDia(usuario, gruposMusculares.get(Random.nextInt(gruposMusculares.size)))
            }
        }
    }

    fun crearDia(usuario: Usuario, grupoMuscular: String): MutableList<String>
    {
        val ejercicios: MutableList<Ejercicio> = db.consultarEjerciciosPorGrupoMuscular(grupoMuscular)
        val resultado : MutableList<String> = mutableListOf()
        if(ejercicios.size == 5){
            for(ejercicio in ejercicios) {
                resultado.add(ejercicio.id)
            }
        } else {
            for(i in 0..4) {
                var index = Random.nextInt(ejercicios.size)
                if(resultado.size > 0) {
                    while(resultado.contains(ejercicios.get(index).id)) {
                        index = Random.nextInt(ejercicios.size)
                    }
                }
                resultado.add(ejercicios.get(index).id)
            }
        }
        return resultado
    }

    fun calcularIMC(usuario: Usuario) : Double {
        return usuario.peso / (usuario.altura * usuario.altura)
    }

    fun calcularHandicapPorIMC(imc: Double) : Double {
        var handicap : Double
        if(imc < 18.5) {
            handicap = 0.9
        } else if (18.5 <= imc && imc <= 25) {
            handicap = 1.0
        } else if (25 < imc && imc < 30) {
            handicap = 0.9
        } else {
            handicap = 0.75
        }
        return handicap
    }

    fun calcularMultiplicadorEstadoFisico(estadoFisico: Int) : Double {
        var multiplicador : Double
        if (estadoFisico == 1) {
            multiplicador = 1.2
        } else if (estadoFisico == 2) {
            multiplicador = 1.5
        } else {
            multiplicador = 1.0
        }
        return multiplicador
    }

    fun calcularCantidadDeRepeticiones(numeroBase: Int, handicap: Double, multiplicador: Double) : Int
    {
        val resultado : Double = numeroBase * handicap * multiplicador
        return resultado.toInt()
    }
}