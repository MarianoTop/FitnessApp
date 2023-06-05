package com.example.fitnessapp.fragments.Registro

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitnessapp.entities.Usuario
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class SharedRegistrarseViewModel : ViewModel() {

    private val db = Firebase.firestore
    private val auth = Firebase.auth

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
                "objetivo",usuario.objetivo,
                "reporteSemanal",usuario.reporteSemanal,
                "nivelFisico",usuario.nivelFisico

            ).await()

        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Exception thrown: ${e.message}")

        }
    }


    suspend fun crearUsuario(email : String, password : String): Boolean {
        var response = false
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            response = true
        }
        catch ( e: Exception ) {
            Log.e(ContentValues.TAG, "Exception thrown: ${e.message}")
        }
        return response
    }

}