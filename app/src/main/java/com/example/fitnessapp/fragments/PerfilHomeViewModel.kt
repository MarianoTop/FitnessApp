package com.example.fitnessapp.fragments

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.fitnessapp.entities.EstadoRutina
import com.example.fitnessapp.entities.Rutina
import com.example.fitnessapp.entities.Usuario
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class PerfilHomeViewModel : ViewModel() {

    private val db = Firebase.firestore

    suspend fun persistirUsuario(usuario: Usuario) {

        try {
            /*https://stackoverflow.com/questions/57924683/firebase-transaction-to-update-multiple-fields-in-a-document-at-once*/
            println(usuario.id)
            val usuarioDb = db.collection("usuarios").document(usuario.id)
            usuarioDb.update(

                "nombre",usuario.nombre,
                "peso",usuario.peso,
                "altura",usuario.altura,
                "edad",usuario.edad,
                "objetivo",usuario.objetivo


            ).await()

        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Exception thrown: ${e.message}")

        }


    }

}