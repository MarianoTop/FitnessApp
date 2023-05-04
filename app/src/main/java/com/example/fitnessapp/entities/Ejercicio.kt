package com.example.fitnessapp.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ejercicio(
    var id: Long,
    var image: String,
    var description :String,
    var cantidad: Int,
    var paraBajarPeso: Int,
    var paraGanarPeso: Int,
    var grupoMuscular: String,
    var subGrupoMuscular: String,
    var caloriasQuemadas: Double
):Parcelable
