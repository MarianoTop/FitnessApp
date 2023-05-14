package com.example.fitnessapp.entities

import android.content.ContentValues
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.toObject

class DbRepository {

    val db = Firebase.firestore

    fun consultarEjercicios(): MutableList<Ejercicio>
    {
        var ejercicios : MutableList<Ejercicio> = arrayListOf()
        db.collection("ejercicios").get().addOnSuccessListener {snapshot ->
            if(snapshot != null) {
                for(ejercicio in snapshot) {
                    if(ejercicio != null)
                    {
                        ejercicios.add(ejercicio.toObject())
                    }
                }
            }
        }.addOnFailureListener { exception ->
            Log.w(ContentValues.TAG, "Error getting documents: ", exception)
        }
        return ejercicios
    }

    fun consultarEjerciciosPorGrupoMuscular(grupoMuscular: String): MutableList<Ejercicio>
    {
        val ejercicios : MutableList<Ejercicio> = arrayListOf()
        db.collection("ejercicios").get().addOnSuccessListener {snapshot ->
            if(snapshot != null) {
                for(ejercicio in snapshot) {
                    ejercicios.add(ejercicio.toObject())
                }
            }
        }.addOnFailureListener { exception ->
            Log.w(ContentValues.TAG, "Error getting documents: ", exception)
        }
        return ejercicios
    }

}