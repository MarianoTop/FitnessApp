package com.example.fitnessapp.entities

class EjercicioRepository {

    var ejercicios:MutableList<Ejercicio>
    init {
        ejercicios= mutableListOf()

        ejercicios.add(Ejercicio(1,"https://media.istockphoto.com/id/578104104/es/vector/paso-a-la-instrucci%C3%B3n-en-push-up.jpg?s=612x612&w=0&k=20&c=RNxtdjWZVPjCdk6dBd4wlgNVX7qB6cPFoakeb1Mux8c=","Flexiones x4"))
        ejercicios.add(Ejercicio(2,"https://media.istockphoto.com/id/578104104/es/vector/paso-a-la-instrucci%C3%B3n-en-push-up.jpg?s=612x612&w=0&k=20&c=RNxtdjWZVPjCdk6dBd4wlgNVX7qB6cPFoakeb1Mux8c=","Flexiones x4"))
        ejercicios.add(Ejercicio(3,"https://media.istockphoto.com/id/578104104/es/vector/paso-a-la-instrucci%C3%B3n-en-push-up.jpg?s=612x612&w=0&k=20&c=RNxtdjWZVPjCdk6dBd4wlgNVX7qB6cPFoakeb1Mux8c=","Flexiones x4"))
        ejercicios.add(Ejercicio(4,"https://media.istockphoto.com/id/578104104/es/vector/paso-a-la-instrucci%C3%B3n-en-push-up.jpg?s=612x612&w=0&k=20&c=RNxtdjWZVPjCdk6dBd4wlgNVX7qB6cPFoakeb1Mux8c=","Flexiones x4"))

    }
}