package com.example.fitnessapp.fragments

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.entities.Ejercicio
import com.example.fitnessapp.entities.Rutina
import com.example.fitnessapp.entities.Semana
import com.example.fitnessapp.entities.Usuario
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class EntrenamientoHomeViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var semanas = MutableLiveData<MutableList<Semana>>()
    private val db = Firebase.firestore

    init{
        val semanasDetalladas : MutableList<Semana> = mutableListOf()

        viewModelScope.launch {
            val semanasDb = getSemanas()
            for(semana in semanasDb) {
                val usuario = getUsuarioDeLaSemana(semana)
                semana.usuario = usuario
                semana.usuarioId = usuario.id
                val rutinasDb = getRutinasPorSemana(semana)
                for(rutina in rutinasDb) {
                    val ejercicios = getEjerciciosPorRutina(rutina)
                    for(ejercicio in ejercicios) {
                        rutina.ejerciciosId.add(ejercicio.id)
                        rutina.ejercicios.add(ejercicio)
                    }
                    semana.rutinasId.add(rutina.id)
                    semana.rutinas.add(rutina)
                }
                semanasDetalladas.add(semana)
            }
            semanas.postValue(semanasDetalladas)
        }
    }

    suspend fun getSemanas(): MutableList<Semana> {
        val semanas = mutableListOf<Semana>()
        return try {
            val semanasDb = db.collection("semanas").get().await()
            for(semana in semanasDb)
            {
                semanas.add(semana.toObject())
            }
            return semanas
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Exception thrown: ${e.message}")
            return semanas
        }
    }
    suspend fun getUsuarioDeLaSemana(semanaObj : Semana): Usuario {
        var usuario : Usuario = Usuario()
        return try {
            val usuariosDb = db.collection("usuarios").whereEqualTo("id", semanaObj.usuarioId).get().await()
            for(usuarioDb in usuariosDb)
            {
                usuario = usuarioDb.toObject()
            }
            return usuario
        } catch (e: Exception)
        {
            Log.e(ContentValues.TAG, "Exception thrown: ${e.message}")
            return usuario
        }

    }
    suspend fun getRutinasPorSemana(semanaObj : Semana): MutableList<Rutina> {
        val rutinas : MutableList<Rutina> = mutableListOf()
        return try {
            val rutinasDb = db.collection("rutinas").whereIn("id", semanaObj.rutinasId).get().await()
            for(rutina in rutinasDb)
            {
                rutinas.add(rutina.toObject())
            }
            return rutinas
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Exception thrown: ${e.message}")
            return rutinas
        }

    }

    suspend fun getEjerciciosPorRutina(rutinaObj : Rutina): MutableList<Ejercicio> {
        val ejercicios : MutableList<Ejercicio> = mutableListOf()
        try {
            val ejerciciosDb = db.collection("ejercicios").whereIn("id", rutinaObj.ejerciciosId).get().await()
            for(ejercicio in ejerciciosDb)
            {
                ejercicios.add(ejercicio.toObject())
            }
            return ejercicios
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Exception thrown: ${e.message}")
            return ejercicios
        }
    }
}