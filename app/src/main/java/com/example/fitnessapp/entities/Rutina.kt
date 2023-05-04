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
    var faltoEjercicio: Boolean
):Parcelable
