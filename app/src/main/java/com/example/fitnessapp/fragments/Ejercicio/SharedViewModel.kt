package com.example.fitnessapp.fragments.Ejercicio

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.entities.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.sql.Timestamp
import java.util.*

class SharedViewModel : ViewModel() {

    lateinit var rutina : Rutina
    private val db = Firebase.firestore
    var diasDeEntrenamiento =mutableListOf(true,false,false,true,true,false,true)

    init {

    }

    var mytime= Timestamp(Date().time)
    var usuario : Usuario = Usuario("1", "test@gmail.com", "test", "test", 90.0, 180.0, 80, 21, "Masculino", 1, 0,mytime,
    0,diasDeEntrenamiento)

    var totalCalorias : Double = 0.0
    var totalTiempo : Double = 0.0
    var posActual : Int = 0
    var horaInicio : Long = 0
    var horaFin : Long = 0

    fun asignarRutina(rutina: Rutina) {
        this.rutina = rutina
    }

    fun resetearPos() {
        posActual = 0
    }
    fun incrementarPos() {
        posActual++
    }

    fun cantEjercicios(): String {
        return rutina.ejercicios.size.toString()
    }

    fun ejercActual(): Ejercicio {
        return rutina.ejercicios[posActual]
    }

    fun ejercAnterior(): Ejercicio {
        return rutina.ejercicios[posActual-1]
    }

    fun rutinaCompletada() {
        rutina.estado = EstadoRutina.COMPLETADA.value
    }

    suspend fun persistirRutinaCompletada() {
        var rutinaEncontrada: Rutina = Rutina()
        try {

            val rutinaDb = db.collection("rutinas").document(rutina.id)
            rutinaDb.update("estado", EstadoRutina.COMPLETADA.value).await()

        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Exception thrown: ${e.message}")

        }


    }

    fun sumaCalorias() {
        var ejercActual = ejercActual()
        totalCalorias += ejercActual.calorias * ejercActual.cantidad
    }

    fun guardarTiempoInicio() {
        horaInicio = System.currentTimeMillis()
    }

    fun guardarTiempoFin() {
        horaFin = System.currentTimeMillis()

        totalTiempo = (horaFin - horaInicio).toDouble()

    }

    suspend fun crearSesion(sesion: Sesion) {
    try {
        db.collection("sesiones").add(sesion).await()
        println("Sesión guardada con éxito")
    } catch (e: Exception) {
        println("Error al agregar sesión: $e")
    }
}

}