package com.example.fitnessapp.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Semana(
    var id: Long,
    var rutinas: MutableList<Rutina>,
    var usuario: Usuario
) : Parcelable

