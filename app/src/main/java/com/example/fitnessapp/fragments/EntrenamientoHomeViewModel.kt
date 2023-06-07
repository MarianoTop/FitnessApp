package com.example.fitnessapp.fragments

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.entities.*
import com.example.fitnessapp.utils.SemanaUtils
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.*

class EntrenamientoHomeViewModel : ViewModel() {
    var semanas = MutableLiveData<MutableList<Semana>>()
    private val db = Firebase.firestore
    private val auth = Firebase.auth

    init{
        val semanasDetalladas : MutableList<Semana> = mutableListOf()

        viewModelScope.launch {
            val usuario = getUsuario(auth.uid!!)
            var semanasDb = getSemanas()
            println("SEMANEITOR " + semanasDb)
            var haySemana = false
            println("HAY SEMANA: " + haySemana)
            if(semanasDb.size == 0) {
                haySemana = false
                println("HAY SEMANA SIZE 0: " + haySemana)
            } else {
                var i = 0
                while (i < semanasDb.size && !haySemana) {
                    haySemana = !semanasDb.get(i).estaFinalizada
                    i++
                }
            }
            //if(!haySemana) {
                //println("llamando manager rutina")
                //val manager = ManagerRutinas()
                //manager.crearSemana(usuario, SemanaUtils.obtenerFechaDiaLunesDeLaSemana(Date()))
                //semanasDb = getSemanas()
            //}
            for(semana in semanasDb) {
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
            val semanasDb = db.collection("semanas").whereEqualTo("usuarioId", auth.uid!!).get().await()
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
    suspend fun getUsuario(userId : String): Usuario {
        var usuario : Usuario = Usuario()
        return try {
            val usuarioDb = db.collection("usuarios").document(userId).get().await()
            usuario = usuarioDb.toObject()!!
            println("USUARIO ---- " + usuario)
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