package com.example.fitnessapp.fragments.Ejercicio

import androidx.lifecycle.ViewModel
import com.example.fitnessapp.entities.Ejercicio
import com.example.fitnessapp.entities.Rutina
import com.example.fitnessapp.entities.Usuario
import java.sql.Timestamp
import java.util.*

class SharedViewModel : ViewModel() {

    lateinit var rutina : Rutina
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

    fun rutinaFinalizada() {
        rutina.finalizada = true
    }

    fun rutinaCompletada() {
        rutina.completado = 1
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
}