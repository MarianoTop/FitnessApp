package com.example.fitnessapp.fragments.Ejercicio

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.entities.Sesion
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ReporteFinEntrenamientoFragment : Fragment() {

    lateinit var v : View

    lateinit var imageLogo : ImageView
    lateinit var textTitle : TextView
    lateinit var textInfo1 : TextView
    lateinit var textInfo2 : TextView
    lateinit var textCalorias : TextView
    lateinit var textDuracion : TextView
    lateinit var btnTerminar : Button
    lateinit var sesion : Sesion
    var totalTiempo : Int = 0
    var totalCalorias : Double = 0.0

    private val sharedViewModel : SharedViewModel by activityViewModels()

    val db = Firebase.firestore
    val sesiones = db.collection("sesiones")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_reporte_fin_entrenamiento, container, false)

        imageLogo = v.findViewById(R.id.imageLogo)
        textTitle = v.findViewById(R.id.textTitle)
        textInfo1 = v.findViewById(R.id.textInfo1)
        textInfo2 = v.findViewById(R.id.textInfo2)
        textCalorias = v.findViewById(R.id.textNroCal)
        textDuracion = v.findViewById(R.id.textDuracion)
        btnTerminar = v.findViewById(R.id.buttonEnd)

        textTitle.text = "Entrenamiento Finalizado"
        textInfo1.text = "Calorias Quemadas"
        textInfo2.text = "Duracion (seg)"
        btnTerminar.text = "Terminar"

        return v
    }

    override fun onStart() {
        super.onStart()

        // Seteamos la rutina en completada
        sharedViewModel.rutina.estado = 1

        // Se despliega en pantalla el total de calorías y el tiempo total de duración de rutina
        totalTiempo = (sharedViewModel.totalTiempo / 1000).toInt()
        totalCalorias = sharedViewModel.totalCalorias
        textCalorias.text = totalCalorias.toString()
        textDuracion.text = totalTiempo.toString()


        // Instanciamos nuevo objeto Sesión con los datos de la rutina terminada
        sesion = Sesion(sharedViewModel.usuario.id, sharedViewModel.rutina.id, totalTiempo.toString(), totalCalorias)


        // Agregamos objeto Sesión a la colección sesiones de la BD
        // Al hacer add() se genera ID único

        sesiones.add(sesion)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "Sesion agregada con el id: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error al agregar sesión", e)
            }

        // Navegación de vuelta a EntrenamientoHomeFragment

        btnTerminar.setOnClickListener(){

            val action =
                ReporteFinEntrenamientoFragmentDirections.actionReporteFinEntrenamientoFragmentToEntrenamientoHomeFragment()
            findNavController().navigateUp()

        }

    }

}