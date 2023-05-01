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
        rutinas.add(Rutina(id = 2, ejercicios = ejercicios, grupoMuscular = "Infeior"))
        rutinas.add(Rutina(id = 3, ejercicios = ejercicios, grupoMuscular = "Completo"))
        rutinas.add(Rutina(id = 4, ejercicios = ejercicios, grupoMuscular = "Brazos"))
        rutinas.add(Rutina(id = 5, ejercicios = ejercicios, grupoMuscular = "Piernas"))
        rutinas.add(Rutina(id = 6, ejercicios = ejercicios, grupoMuscular = "Espalda"))
        rutinas.add(Rutina(id = 7, ejercicios = ejercicios, grupoMuscular = "Pecho"))
        rutinas.add(Rutina(id = 8, ejercicios = ejercicios, grupoMuscular = "Superior"))


    }
}