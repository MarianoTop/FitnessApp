package com.example.fitnessapp.fragments

import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.entities.Ejercicio
import com.example.fitnessapp.entities.Rutina
import com.example.fitnessapp.entities.Semana
import com.example.fitnessapp.entities.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class IniciarSesionViewModel : ViewModel() {
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore

    init {
        auth = Firebase.auth
    }

    suspend fun iniciarSesion(email: String, contrasenia: String): FirebaseUser? {
        println("Iniciando sesion...")
        try {
            val logIn = auth.signInWithEmailAndPassword(email, contrasenia).await()
            Log.d(ContentValues.TAG, "signInWithEmail:success")
            return logIn.user
        } catch (e: Exception) {
            // If sign in fails, display a message to the user.
            Log.w(ContentValues.TAG, "signInWithEmail:failure", e)
            return null
        }
    }

    suspend fun buscarUsuarioEnDB(documento: String) : Boolean {
        return try {
            val usuariosDb = db.collection("usuarios").document(documento).get().await()
            return (usuariosDb.exists())
        } catch (e: Exception)
        {
            Log.e(ContentValues.TAG, "Exception thrown: ${e.message}")
            return false
        }
    }

}