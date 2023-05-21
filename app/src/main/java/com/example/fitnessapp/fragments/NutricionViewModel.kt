package com.example.fitnessapp.fragments

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.fitnessapp.entities.Comida
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class NutricionViewModel : ViewModel() {

    val db = Firebase.firestore

    lateinit var comidasDesayunoMerienda : MutableList<Comida>
    lateinit var comidasAlmuerzoCena : MutableList<Comida>

    fun listarComidas() {

        db.collection("comidas").get().addOnSuccessListener { comidasObtenidas ->
            if (comidasObtenidas != null) {
                for (comida in comidasObtenidas) {
                    var comidaObj : Comida = comida.toObject()
                    if (comidaObj.tipo == 0) {
                        comidasDesayunoMerienda.add(comidaObj)
                    } else {
                        comidasAlmuerzoCena.add(comidaObj)
                    }
                }
            }
        }.addOnFailureListener { exception ->
            Log.w(ContentValues.TAG, "Error getting documents: ", exception)
        }
    }
}