package com.example.fitnessapp.entities

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.random.Random

class ManagerRutinas {

    val db = Firebase.firestore

    fun crearSemana(usuario: Usuario, fechaInicio : Date) {
        println("--- CREANDO SEMANA ---")
        val gruposMusculares: MutableList<String> = mutableListOf()
        gruposMusculares.add("Piernas")
        gruposMusculares.add("Pecho")
        gruposMusculares.add("Abdominal")

        val rutinas : MutableList<Rutina> = mutableListOf()
        val zonaEjercitada : MutableList<String> = mutableListOf()

        for(indiceDia in usuario.diasDeEntrenamiento.indices)
        {
            if(usuario.diasDeEntrenamiento.get(indiceDia))
            {
                println("Dia de ejercicio " + indiceDia)
                var musculo = ""
                do {
                    musculo = gruposMusculares.get(Random.nextInt(gruposMusculares.size))
                } while((musculo == "" || musculo in zonaEjercitada) && zonaEjercitada.size < gruposMusculares.size)

                val ejercicios: MutableList<Ejercicio> = mutableListOf()
                val ejerciciosId : MutableList<String> = mutableListOf()
                val ejerciciosSeleccionados : MutableList<Ejercicio> = mutableListOf()

                db.collection("ejercicios").whereEqualTo("grupoMuscular", musculo).get().addOnSuccessListener {snapshot ->
                    if(snapshot != null) {
                        for(ejercicio in snapshot) {
                            ejercicios.add(ejercicio.toObject())
                        }
                        if(ejercicios.size == 5){
                            for(ejercicio in ejercicios) {
                                ejerciciosId.add(ejercicio.id)
                                ejerciciosSeleccionados.add(ejercicio)
                            }
                        } else {
                            for(i in 0..4) {
                                var index = Random.nextInt(ejercicios.size)
                                if(ejerciciosId.size > 0) {
                                    while(ejerciciosId.contains(ejercicios.get(index).id)) {
                                        index = Random.nextInt(ejercicios.size)
                                    }
                                }
                                ejerciciosId.add(ejercicios.get(index).id)
                                ejerciciosSeleccionados.add(ejercicios.get(index))
                            }
                        }
                        val rutinaDelDia = Rutina("", ejerciciosSeleccionados, ejerciciosId, musculo, 0, 340.0)

                        zonaEjercitada.add(musculo)
                        rutinas.add(rutinaDelDia)

                        if(indiceDia == usuario.diasDeEntrenamiento.size - 1)
                        {
                            val nuevaSemana = Semana("", rutinas, mutableListOf(), usuario, usuario.id, false, fechaInicio)
                            for(i in nuevaSemana.rutinas.indices)
                            {
                                val rutina = nuevaSemana.rutinas.get(i)
                                var rutinaRef : DocumentReference
                                if(rutina.id.isNotEmpty())
                                {
                                    rutinaRef = db.collection("rutinas").document(rutina.id)
                                }
                                else {
                                    rutinaRef = db.collection("rutinas").document()
                                    rutina.id = rutinaRef.id
                                }
                                rutinaRef.set(rutina).addOnSuccessListener {
                                    nuevaSemana.rutinasId.add(rutinaRef.id)
                                    if(i == nuevaSemana.rutinas.size - 1)
                                    {
                                        val semanaRef = db.collection("semanas").document()
                                        nuevaSemana.id = semanaRef.id
                                        semanaRef.set(nuevaSemana).addOnSuccessListener {
                                            Log.d(TAG, "DocumentSnapshot successfully written!")
                                        }.addOnFailureListener {
                                                e -> Log.w(TAG, "Error writing document", e)
                                        }
                                    }
                                }.addOnFailureListener { e ->
                                    Log.w(TAG, "Error writing document", e)
                                }
                            }
                        }
                    }
                }.addOnFailureListener { exception ->
                    Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                }
            }
            else
            {
                println("Descanso " + indiceDia)
                val descanso = Rutina()
                descanso.id = "descanso"
                descanso.totalCalorias = 0.0
                rutinas.add(descanso)
                println("INDICE DIA: " + indiceDia + " || dias de entrenamiento: " + usuario.diasDeEntrenamiento.size)
                if(indiceDia == usuario.diasDeEntrenamiento.size - 1)
                {
                    println("PERSISTIENDO")
                    val nuevaSemana = Semana("", rutinas, mutableListOf(), usuario, usuario.id, false, fechaInicio)
                    for(i in nuevaSemana.rutinas.indices)
                    {
                        val rutina = nuevaSemana.rutinas.get(i)
                        var rutinaRef : DocumentReference
                        if(rutina.id.isNotEmpty())
                        {
                            rutinaRef = db.collection("rutinas").document(rutina.id)
                        }
                        else {
                            rutinaRef = db.collection("rutinas").document()
                            rutina.id = rutinaRef.id
                        }
                        println("ANTES DE GUARDAR")
                        rutinaRef.set(rutina).addOnSuccessListener {
                            println("Rutina guardada ok. i = " + i + " rutinas.size = " + nuevaSemana.rutinas.size + " ID : " + rutina.id)
                            nuevaSemana.rutinasId.add(rutinaRef.id)
                            if(i == nuevaSemana.rutinas.size - 1)
                            {
                                println("PERSISTIENDO 2")
                                val semanaRef = db.collection("semanas").document()
                                nuevaSemana.id = semanaRef.id
                                semanaRef.set(nuevaSemana).addOnSuccessListener {
                                    Log.d(TAG, "DocumentSnapshot successfully written!")
                                }.addOnFailureListener {
                                        e -> Log.w(TAG, "Error writing document", e)
                                }
                            }
                        }.addOnFailureListener { e ->
                            Log.w(TAG, "Error writing document", e)
                        }
                    }
                }
            }
        }
    }

    fun calcularCantidadDeRepeticiones(ejercicio: Ejercicio, usuario: Usuario) : Int
    {
        val resultado : Double
        val cantidadBase : Int = ejercicio.cantidad
        val handicap : Double = calcularHandicapPorIMC(calcularIMC(usuario))
        val multiplicador : Double = calcularMultiplicadorEstadoFisico(usuario.nivelFisico)
        resultado = cantidadBase * handicap * multiplicador
        return resultado.toInt()
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

}