package com.example.fitnessapp.fragments.Ejercicio

import android.os.Bundle
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
import com.example.fitnessapp.fragments.Ejercicio.ReporteFinEntrenamientoFragmentDirections

class ReporteFinEntrenamientoFragment : Fragment() {

    lateinit var v : View

    lateinit var imageLogo : ImageView
    lateinit var textTitle : TextView
    lateinit var textDia : TextView
    lateinit var textInfo1 : TextView
    lateinit var textInfo2 : TextView
    lateinit var textCalorias : TextView
    lateinit var textDuracion : TextView
    lateinit var btnTerminar : Button
    lateinit var sesion : Sesion
    var totalTiempo : Int = 0
    var totalCalorias : Double = 0.0

    private val sharedViewModel : SharedViewModel by activityViewModels()

    // val db = firebase.firestore / ACTIVAR ESTO CUANDO TENGAMOS CONEXIÓN CON LA BD
    // val sesiones = db.collection("sesiones") / ACTIVAR ESTO CUADNO TENGAMOS CONEXIÓN CON LA BD

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_reporte_fin_entrenamiento, container, false)

        imageLogo = v.findViewById(R.id.imageLogo)
        textTitle = v.findViewById(R.id.textTitle)
        textDia = v.findViewById(R.id.textDia)
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
        sharedViewModel.rutina.completado = 1

        // Se despliega en pantalla el total de calorías y el tiempo total de duración de rutina
        totalTiempo = (sharedViewModel.totalTiempo / 1000).toInt()
        totalCalorias = sharedViewModel.totalCalorias
        textCalorias.text = totalCalorias.toString()
        textDuracion.text = totalTiempo.toString()

        // Instanciamos nuevo objeto Sesión con los datos de la rutina terminada
        sesion = Sesion(usuario = sharedViewModel.usuario, rutina = sharedViewModel.rutina, duracionSesion = totalTiempo.toString(), caloriasQuemadas = totalCalorias)

        // Agregamos objeto Sesión a la colección sesiones de la BD
        // Al hacer add() se genera ID único
        // Descomentar este código cuando la conexión con la BD esté hecha
        /*
        sesiones.add(sesion)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "Sesion agregada con el id: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error al agregar sesión", e)
            }
        */

        // Navegación de vuelta a EntrenamientoHomeFragment
        // HAY QUE PROBARLO CUANDO LUCHO GOBO TERMINE FRAGMENTS DE EJERCICIO

        btnTerminar.setOnClickListener(){

            val action =
                ReporteFinEntrenamientoFragmentDirections.actionReporteFinEntrenamientoFragmentToEntrenamientoHomeFragment()
            findNavController().navigate(action)

            /*
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentName = "EntrenamientoHomeFragment"
            var backStackEntry = fragmentManager.getBackStackEntryAt(0)

            for (i in 0 until fragmentManager.backStackEntryCount) {
                if (fragmentManager.getBackStackEntryAt(i).name == fragmentName) {
                    backStackEntry = fragmentManager.getBackStackEntryAt(i - 1)
                    break
                }
            }

             */
        }

    }

}