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
    private lateinit var db : FirebaseFirestore
    var usuario = MutableLiveData<Usuario?>()


    init {
        auth = Firebase.auth
        db = Firebase.firestore
    }

    fun obtenerUsuario(email: String, contrasenia: String)
    {
        viewModelScope.launch{
            val userAuth = iniciarSesion(email, contrasenia)
            println("AUTH : " + userAuth)
            if(userAuth != null) {
                val user = getUsuarioPorId(userAuth.uid)
                println("USUARIO : " + user)
                usuario.postValue(user)
            } else {
                usuario.postValue(null)
            }
        }
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

    suspend fun getUsuarioPorId(id : String): Usuario? {
        try {
            val usuariosDb = db.collection("usuarios").whereEqualTo("id", id).get().await()
            var usuario = Usuario()
            for(usuarioDb in usuariosDb)
            {
                usuario = usuarioDb.toObject()
            }
            return usuario
        } catch (e: Exception)
        {
            Log.e(ContentValues.TAG, "Exception thrown: ${e.message}")
            return null
        }

    }

}