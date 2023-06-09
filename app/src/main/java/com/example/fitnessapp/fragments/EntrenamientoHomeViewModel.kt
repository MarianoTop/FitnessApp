package com.example.fitnessapp.fragments

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.entities.*
import com.example.fitnessapp.utils.SemanaUtils
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.*
import kotlin.collections.HashMap
import kotlin.random.Random

class EntrenamientoHomeViewModel : ViewModel() {
    var semanas = MutableLiveData<MutableList<Semana>>()
    lateinit var usuarioApp : Usuario
    private val db = Firebase.firestore
    private val auth = Firebase.auth

    init{

        viewModelScope.launch {
            usuarioApp = getUsuario(auth.uid!!)
            val semanasDb = validarSemanas(usuarioApp)
            buscarDatos(semanasDb, usuarioApp)
        }
    }

    suspend fun validarSemanas(usuario : Usuario) : MutableList<Semana>{
        var semanasDb = getSemanas()
        var haySemana = false
        if(semanasDb.size == 0) {
            haySemana = false
        } else {
            var i = 0
            while (i < semanasDb.size && !haySemana) {
                haySemana = !semanasDb.get(i).estaFinalizada
                i++
            }
        }
        if(!haySemana) {
            val resultado = crearSemana(usuario, SemanaUtils.obtenerFechaDiaLunesDeLaSemana(Date()))
            if (resultado)
            {
                return getSemanas()
            }
        }
        return semanasDb
    }
    suspend fun buscarDatos(semanasDb : MutableList<Semana>, usuario : Usuario) {
        val semanasDetalladas: MutableList<Semana> = mutableListOf()
        for (semana in semanasDb) {
            semana.usuario = usuario
            semana.usuarioId = usuario.id
            val semanaValidada = validarSemana(semana)
            semanasDetalladas.add(semanaValidada)
        }
        var hayPendiente = false
        var semanaIndex = 0
        while(semanaIndex < semanasDetalladas.size && !hayPendiente){
            hayPendiente = !semanasDetalladas.get(semanaIndex).estaFinalizada
            semanaIndex++
        }
        println("HAY PENDIENTE ? " + hayPendiente)
        if(!hayPendiente) {
            crearSemana(usuario, SemanaUtils.obtenerFechaDiaLunesDeLaSemana(Date()))
            val semanasReobtenidas = getSemanas()
            for (semanasIndex in 0 until semanasReobtenidas.size)
            {
                var semanaEncontrada = false
                for(semana in semanasDetalladas)
                {
                    if(semanasReobtenidas.get(semanasIndex).id == semana.id )
                    {
                        semanaEncontrada = true
                    }
                }
                if(!semanaEncontrada){
                    val semanaValidada = validarSemana(semanasReobtenidas.get(semanasIndex))
                    semanasDetalladas.add(semanaValidada)
                }
            }
        }
        semanas.postValue(semanasDetalladas)
    }

    suspend fun validarSemana(semana : Semana) : Semana {
        val rutinasDb = getRutinasPorSemana(semana)
        var i = 0;
        var rutina: Rutina = Rutina()
        while(i <  semana.rutinasId.size) {
            if (semana.rutinasId.get(i) != "descanso") {
                var j = 0
                var rutinaEncontrada = false
                while (!rutinaEncontrada && j < rutinasDb.size) {
                    if (semana.rutinasId.get(i) == rutinasDb.get(j).id) {
                        rutina = rutinasDb.get(j)
                        val ejercicios = getEjerciciosPorRutina(rutina)
                        for (ejercicio in ejercicios) {
                            ejercicio.cantidad = calcularCantidadDeRepeticiones(ejercicio, semana.usuario)
                            rutina.ejercicios.add(ejercicio)
                        }
                        rutinaEncontrada = true
                    }
                    j++
                }
            } else {
                var j = 0
                var descanso = Rutina()
                descanso.id = "-1"
                while (descanso.id != "descanso") {
                    descanso = rutinasDb.get(j)
                    j++
                }
                rutina = descanso
            }
            semana.rutinas.add(rutina)
            i++
        }
        SemanaUtils.validarSemana(semana)

        return semana
    }

    suspend fun getSemanas(): MutableList<Semana> {
        val semanas = mutableListOf<Semana>()
        return try {
            val semanasDb = db.collection("semanas").whereEqualTo("usuarioId", auth.uid!!).orderBy("fechaInicio").get().await()
            for(semana in semanasDb)
            {
                semanas.add(semana.toObject())
            }
            return semanas
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Exception thrown: ${e.message}")
            return semanas
        }
    }
    suspend fun getUsuario(userId : String): Usuario {
        var usuario : Usuario = Usuario()
        return try {
            val usuarioDb = db.collection("usuarios").document(userId).get().await()
            usuario = usuarioDb.toObject()!!
            return usuario
        } catch (e: Exception)
        {
            Log.e(ContentValues.TAG, "Exception thrown: ${e.message}")
            return usuario
        }
    }

    suspend fun getRutinasPorSemana(semanaObj : Semana): MutableList<Rutina> {
        val rutinas : MutableList<Rutina> = mutableListOf()
        return try {
            val rutinasDb = db.collection("rutinas").whereIn("id", semanaObj.rutinasId).get().await()
            for(rutina in rutinasDb)
            {
                rutinas.add(rutina.toObject())
            }
            return rutinas
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Exception thrown: ${e.message}")
            return rutinas
        }

    }

    suspend fun getEjerciciosPorRutina(rutinaObj : Rutina): MutableList<Ejercicio> {
        val ejercicios : MutableList<Ejercicio> = mutableListOf()
        try {
            val ejerciciosDb = db.collection("ejercicios").whereIn("id", rutinaObj.ejerciciosId).get().await()
            for(ejercicio in ejerciciosDb)
            {
                ejercicios.add(ejercicio.toObject())
            }
            return ejercicios
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Exception thrown: ${e.message}")
            return ejercicios
        }
    }

    suspend fun getEjerciciosPorGrupoMuscular(musculo : String): MutableList<Ejercicio> {
        val ejercicios : MutableList<Ejercicio> = mutableListOf()
        try {
            val ejerciciosDb = db.collection("ejercicios").whereEqualTo("grupoMuscular", musculo).get().await()
            for(ejercicio in ejerciciosDb)
            {
                ejercicios.add(ejercicio.toObject())
            }
            return ejercicios
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Exception thrown: ${e.message}")
            return ejercicios
        }
    }

    suspend fun persistirRutina(rutina: Rutina) : Rutina{
        var rutinaRef : DocumentReference
        if(rutina.id.isNotEmpty())
        {
            rutinaRef = db.collection("rutinas").document(rutina.id)
        }
        else {
            rutinaRef = db.collection("rutinas").document()
            rutina.id = rutinaRef.id
        }
        try {
            val data = hashMapOf(
                "id" to rutina.id,
                "ejerciciosId" to rutina.ejerciciosId,
                "estado" to rutina.estado,
                "grupoMuscular" to rutina.grupoMuscular,
                "totalCalorias" to rutina.totalCalorias
            )
            rutinaRef.set(data).await()
            return rutina
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Exception thrown: ${e.message}")
            rutina.id = ""
            return rutina
        }
    }

    suspend fun persistirSemana(nuevaSemana: Semana) : Boolean{
        val semanaRef = db.collection("semanas").document()
        nuevaSemana.id = semanaRef.id
        try {
            val data = hashMapOf(
                "estaFinalizada" to false,
                "fechaInicio" to nuevaSemana.fechaInicio,
                "id" to nuevaSemana.id,
                "rutinasId" to nuevaSemana.rutinasId,
                "usuarioId" to nuevaSemana.usuarioId
            )
            semanaRef.set(data).await()
            return true
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error writing document", e)
            return false
        }
    }

    suspend fun crearSemana(usuario: Usuario, fechaInicio : Date) : Boolean {
        val gruposMusculares: MutableList<String> = mutableListOf()
        gruposMusculares.add("Piernas")
        gruposMusculares.add("Pecho")
        gruposMusculares.add("Abdominal")
        val rutinas : MutableList<Rutina> = mutableListOf()
        val zonaEjercitada : MutableList<String> = mutableListOf()
        var resultado = false

        for(indiceDia in usuario.diasDeEntrenamiento.indices)
        {
            if(usuario.diasDeEntrenamiento.get(indiceDia))
            {
                var musculo = ""
                do {
                    val rndm = Random.nextInt(gruposMusculares.size)
                    musculo = gruposMusculares.get(rndm)
                } while((musculo == "" || musculo in zonaEjercitada) && zonaEjercitada.size < gruposMusculares.size)

                val ejercicios: MutableList<Ejercicio> = mutableListOf()
                val ejerciciosId : MutableList<String> = mutableListOf()
                val ejerciciosSeleccionados : MutableList<Ejercicio> = mutableListOf()
                val ejerciciosDb = getEjerciciosPorGrupoMuscular(musculo)
                for(ejercicio in ejerciciosDb) {
                    ejercicios.add(ejercicio)
                }
                if(ejercicios.size == 5){
                    for(ejercicio in ejercicios) {
                        ejerciciosId.add(ejercicio.id)
                        ejerciciosSeleccionados.add(ejercicio)
                    }
                } else {
                    for(i in 0..4) {
                        var index = Random.nextInt(ejercicios.size)
                        if(ejerciciosId.size > 0) {
                            while(ejerciciosId.contains(ejercicios.get(index).id)) {
                                index = Random.nextInt(ejercicios.size)
                            }
                        }
                        ejerciciosId.add(ejercicios.get(index).id)
                        ejerciciosSeleccionados.add(ejercicios.get(index))
                    }
                }
                val rutinaDelDia = Rutina("", ejerciciosSeleccionados, ejerciciosId, musculo, 0, 340.0)

                zonaEjercitada.add(musculo)
                rutinas.add(rutinaDelDia)

                if(indiceDia == usuario.diasDeEntrenamiento.size - 1)
                {
                    val nuevaSemana = Semana("", rutinas, mutableListOf(), usuario, usuario.id, false, fechaInicio)
                    resultado = persistirDatos(nuevaSemana)
                }

            }
            else
            {
                val descanso = Rutina()
                descanso.id = "descanso"
                descanso.estado = 3
                descanso.totalCalorias = 0.0
                rutinas.add(descanso)
                if(indiceDia == usuario.diasDeEntrenamiento.size - 1)
                {
                    if(indiceDia == usuario.diasDeEntrenamiento.size - 1)
                    {
                        val nuevaSemana = Semana("", rutinas, mutableListOf(), usuario, usuario.id, false, fechaInicio)
                        resultado = persistirDatos(nuevaSemana)
                    }
                }
            }
        }
        return resultado
    }

    suspend fun persistirDatos(nuevaSemana: Semana) : Boolean {
        for(i in nuevaSemana.rutinas.indices)
        {
            var rutina = nuevaSemana.rutinas.get(i)
            rutina = persistirRutina(rutina)

            if(rutina.id != "")
            {
                nuevaSemana.rutinasId.add(rutina.id)
                if(i == nuevaSemana.rutinas.size - 1)
                {
                    val res = persistirSemana(nuevaSemana)
                    return res
                }
            } else {
                Log.w(ContentValues.TAG, "Error persistiendo rutina")
                return false
            }
        }
        return false
    }

    fun calcularCantidadDeRepeticiones(ejercicio: Ejercicio, usuario: Usuario) : Int
    {
        val resultado : Double
        val cantidadBase : Int = ejercicio.cantidad
        val handicap : Double = calcularHandicapPorIMC(calcularIMC(usuario))
        val multiplicador : Double = calcularMultiplicadorEstadoFisico(usuario.nivelFisico)
        resultado = cantidadBase * handicap * multiplicador
        return resultado.toInt()
    }

    fun calcularIMC(usuario: Usuario) : Double {
        return usuario.peso / (usuario.altura * usuario.altura)
    }

    fun calcularHandicapPorIMC(imc: Double) : Double {
        var handicap : Double
        if(imc < 18.5) {
            handicap = 0.9
        } else if (18.5 <= imc && imc <= 25) {
            handicap = 1.0
        } else if (25 < imc && imc < 30) {
            handicap = 0.9
        } else {
            handicap = 0.75
        }
        return handicap
    }

    fun calcularMultiplicadorEstadoFisico(estadoFisico: Int) : Double {
        var multiplicador : Double
        if (estadoFisico == 1) {
            multiplicador = 1.2
        } else if (estadoFisico == 2) {
            multiplicador = 1.5
        } else {
            multiplicador = 1.0
        }
        return multiplicador
    }

}