package com.example.fitnessapp.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rutina(
    var id: Long,
    var ejercicios: MutableList<Ejercicio>,
    var grupoMuscular: String,
    var finalizada: Boolean,
    var esDescanso: Boolean,
    var faltoEjercicio: Boolean,
    var completado: Int, // Valores: 0 falta completar, 1 completada, 2 ausente
    var totalCalorias: Double,
):Parcelable