package com.example.fitnessapp.repository

import com.example.fitnessapp.entities.Ejercicio
import com.example.fitnessapp.entities.EjercicioRepository
import com.example.fitnessapp.entities.Rutina

class RutinaRepository {

    var rutinas : MutableList<Rutina>;
    var ejercicios : MutableList<Ejercicio> = EjercicioRepository().ejercicios;

    init {
        rutinas = mutableListOf();
        rutinas.add(Rutina(id = 1, ejercicios = ejercicios, grupoMuscular = "Superior"))

    }
}