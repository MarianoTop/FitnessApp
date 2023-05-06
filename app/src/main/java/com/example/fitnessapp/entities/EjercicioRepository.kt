package com.example.fitnessapp.entities

class EjercicioRepository {

    val db: DbRepository = DbRepository();
    var ejercicios: MutableList<Ejercicio>

    init {
        ejercicios= mutableListOf()

        ejercicios.add(Ejercicio("1","https://media.istockphoto.com/id/578104104/es/vector/paso-a-la-instrucci%C3%B3n-en-push-up.jpg?s=612x612&w=0&k=20&c=RNxtdjWZVPjCdk6dBd4wlgNVX7qB6cPFoakeb1Mux8c=","Flexiones x4",3,true,true,"Piernas","Gluteos"))
        ejercicios.add(Ejercicio("2","data:image/png;base64,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","Flexiones x4",3,true,true,"Piernas","Gluteos"))
        ejercicios.add(Ejercicio("3","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQib5CBITojtTcUYSAO5NbznvU6yO6XT3bf4g&usqp=CAU","Flexiones x4",3,true,true,"Piernas","Gluteos"))
        ejercicios.add(Ejercicio("4","https://media.istockphoto.com/id/578104104/es/vector/paso-a-la-instrucci%C3%B3n-en-push-up.jpg?s=612x612&w=0&k=20&c=RNxtdjWZVPjCdk6dBd4wlgNVX7qB6cPFoakeb1Mux8c=","Flexiones x4",3,true,true,"Piernas","Gluteos"))
    }

    fun consultarEjercicios(): MutableList<Ejercicio> {
        val resultado : MutableList<Ejercicio> = db.consultarEjercicios();
        return resultado
    }
}