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
    var finalizada: Boolean,
    var esDescanso: Boolean,
    var faltoEjercicio: Boolean,
    var completado: Int, // Valores: 0 falta completar, 1 completada, 2 ausente
    var totalCalorias: Double,
):Parcelable {
    constructor() : this("", mutableListOf(),  mutableListOf(), "", false, false, false, 0, 0.0)
}
