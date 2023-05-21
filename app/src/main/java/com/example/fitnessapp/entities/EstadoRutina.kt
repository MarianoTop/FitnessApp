package com.example.fitnessapp.entities

enum class EstadoRutina {
    FALTA_COMPLETAR(0), COMPLETADA(1),AUSENTE(2),ES_DESCANSO(3);


    var value: Int
    constructor(
        value: Int,

    ) {
        this.value = value

    }
}