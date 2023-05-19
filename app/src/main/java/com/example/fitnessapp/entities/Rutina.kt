package com.example.fitnessapp.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rutina(
    var id: String,
    var ejercicios: MutableList<Ejercicio>,
    var ejerciciosId: MutableList<String>,
    var grupoMuscular: String,
    // Hay que eliminar los tres de abajo y refactorizar codigo

    var estado: Int, // Valores: 0 falta completar, 1 completada, 2 ausente, 3 es descanso
    var totalCalorias: Double,
):Parcelable {
    constructor() : this("", mutableListOf(),  mutableListOf(), "",  0, 0.0)
}
