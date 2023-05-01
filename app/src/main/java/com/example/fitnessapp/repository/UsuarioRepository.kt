package com.example.fitnessapp.repository

import com.example.fitnessapp.entities.Usuario
import java.text.SimpleDateFormat
import java.util.*

class UsuarioRepository {

    var usuarios : MutableList<Usuario>;
    var diasDeEntrenamiento : MutableList<Boolean> = mutableListOf();

    init {
        usuarios = mutableListOf();
        diasDeEntrenamiento.add(true)
        diasDeEntrenamiento.add(false)
        usuarios.add(Usuario(
            id = 1,
            nombre = "Juan123",
            contraseña = "12345",
            peso = 65.00,
            altura = 180.00,
            objetivo = 1,
            edad = 27,
            genero = "Masculino",
            reporteSemanal = 1,
            reporteMensual = 1,
            hora = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar
                .getInstance().getTime()),
            diasDeEntrenamiento =diasDeEntrenamiento





        ))
    }
}