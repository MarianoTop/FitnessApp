package com.example.fitnessapp.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comida(
    var id : String,
    var nombre : String,
    var imagen : String,
    var calorias : Double,
    var ingredientes : MutableList<String>,
    var tipo : Int, // Valores: 0 desayuno/merienda, 1 almuerzo/cena
): Parcelable {
    constructor() : this("", "", "", 0.0, mutableListOf(), 0)

}