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
    val usuario = Usuario()

    suspend fun persistirUsuario(usuario: Usuario): Boolean {
        usuario.id = auth.uid!!
        try {
            db.collection("usuarios").document(auth.uid!!).set(usuario).await()
            return true
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Exception thrown: ${e.message}")
            return false
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