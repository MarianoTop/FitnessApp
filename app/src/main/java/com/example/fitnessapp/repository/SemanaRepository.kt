package com.example.fitnessapp.repository

import com.example.fitnessapp.entities.Rutina
import com.example.fitnessapp.entities.Semana
import com.example.fitnessapp.entities.Usuario

class SemanaRepository {

    var semanas : MutableList<Semana>;
    var rutinas : MutableList<Rutina> = RutinaRepository().rutinas;
    var usuarios : MutableList<Usuario> = UsuarioRepository().usuarios

    init {
        semanas = mutableListOf();
        semanas.add(Semana(id = 1, rutinas = rutinas, usuarios[0]))
        semanas.add(Semana(id = 2, rutinas = rutinas, usuarios[0]))
        semanas.add(Semana(id = 3, rutinas = rutinas, usuarios[0]))
        semanas.add(Semana(id = 4, rutinas = rutinas, usuarios[0]))
        semanas.add(Semana(id = 5, rutinas = rutinas, usuarios[0]))
        semanas.add(Semana(id = 6, rutinas = rutinas, usuarios[0]))
        semanas.add(Semana(id = 7, rutinas = rutinas, usuarios[0]))
        semanas.add(Semana(id = 8, rutinas = rutinas, usuarios[0]))

    }
}