package com.example.fitnessapp.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ejercicio(
    var id: String,
    var image: String,
    var nombre :String,
    var cantidad: Int,
    var calorias: Double,
    var bajarPeso: Boolean,
    var ganarMasa: Boolean,
    var grupoMuscular: String,
    var subGrupoMuscular: String
): Parcelable
{
    constructor(): this("", "", "", 0,0.0,false,false,"","")
}
