package com.example.fitnessapp.entities

class EjercicioRepository {

    var ejercicios:MutableList<Ejercicio>
    init {
        ejercicios= mutableListOf()

        ejercicios.add(Ejercicio("1","https://www.yomeentreno.com/wp-content/uploads/2017/07/slowclap.gif","Lagartijas con aplauso",10,
            1.0,true,false,"Pecho", subGrupoMuscular = "Pectoral mayor"))
        ejercicios.add(Ejercicio("2","https://www.yomeentreno.com/wp-content/uploads/2017/01/AbdominalBicicleta.gif","Abdominales bicicleta",20,
            1.0,true,true,"Abdominal", subGrupoMuscular = "Recto abdominal, oblicuos, flexores de cadera mayor"))
        ejercicios.add(Ejercicio("3","https://i0.wp.com/post.medicalnewstoday.com/wp-content/uploads/sites/3/2022/02/HL-08.02.LyingAbduction.gif?w=315&h=840",
            "Abducción de cadera",17,
            1.0,true,true,"Piernas", subGrupoMuscular = "Abductores"))
        ejercicios.add(Ejercicio("4","https://static.wixstatic.com/media/c94d75_031d642fa07d4293a8e230eefdaf7d66~mv2.gif","Plancha lateral con crunch ",15,
            1.0,true,false,"Abdominal", subGrupoMuscular = "Recto abdominal, oblicuos, flexores de cadera"))

    }
}