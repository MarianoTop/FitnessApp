package com.example.fitnessapp.fragments.Registro

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.ToggleButton
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R

class RegistrarseP4Fragment : Fragment() {

    lateinit var v : View

    private val sharedViewModel : SharedRegistrarseViewModel by activityViewModels()
    lateinit private var btnGanarMasa : Button
    lateinit private var btnBajarPeso : Button
    lateinit private var toggleLunes : ToggleButton
    lateinit private var toggleMartes : ToggleButton
    lateinit private var toggleMiercoles : ToggleButton
    lateinit private var toggleJueves : ToggleButton
    lateinit private var toggleViernes : ToggleButton
    lateinit private var toggleSabado : ToggleButton
    lateinit private var toggleDomingo : ToggleButton
    lateinit private var btnSiguiente : Button
    private var objetivo : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_registrarse_p4, container, false)
        btnGanarMasa = v.findViewById(R.id.btnGanarMasa)
        btnBajarPeso = v.findViewById(R.id.btnBajarPeso)
        toggleLunes = v.findViewById(R.id.toggleLunes)
        toggleMartes = v.findViewById(R.id.toggleMartes)
        toggleMiercoles = v.findViewById(R.id.toggleMiercoles)
        toggleJueves = v.findViewById(R.id.toggleJueves)
        toggleViernes = v.findViewById(R.id.toggleViernes)
        toggleSabado = v.findViewById(R.id.toggleSabado)
        toggleDomingo = v.findViewById(R.id.toggleDomingo)
        btnSiguiente = v.findViewById(R.id.btnSiguiente)

        return v
    }

    override fun onStart() {
        super.onStart()
        btnGanarMasa.setOnClickListener {
            objetivo = "Masa"
            btnGanarMasa.setBackgroundColor(0xff960000.toInt())
            btnBajarPeso.setBackgroundColor(0xff969695.toInt())
        }
        btnBajarPeso.setOnClickListener{
            objetivo = "Peso"
            btnGanarMasa.setBackgroundColor(0xff969695.toInt())
            btnBajarPeso.setBackgroundColor(0xff960000.toInt())
        }
        btnSiguiente.setOnClickListener {
            if(validar()) {
                //Bajar de peso = 0, Aumentar masa muscular = 1
                if(objetivo == "Masa") {
                    sharedViewModel.usuario.objetivo = 1
                } else if(objetivo == "Peso") {
                    sharedViewModel.usuario.objetivo = 0
                } else {
                    throw Error("Error en el objetivo")
                }
                sharedViewModel.usuario.diasDeEntrenamiento = obtenerDiasDeEntrenamiento()
                val action = RegistrarseP4FragmentDirections.actionRegistrarseP4FragmentToRegistrarseP5Fragment()
                findNavController().navigate(action)
            }
        }
    }

    private fun validar() : Boolean{
        if(objetivo == "") {
            Toast.makeText(context, "Debe seleccionar un objetivo.", Toast.LENGTH_SHORT,).show()
        } else if (!toggleLunes.isChecked && !toggleMartes.isChecked && !toggleMiercoles.isChecked && !toggleJueves.isChecked && !toggleViernes.isChecked && !toggleSabado.isChecked && !toggleDomingo.isChecked) {
            Toast.makeText(context, "Debe seleccionar al menos un día para entrenar.", Toast.LENGTH_SHORT,).show()
        }
        return objetivo != "" && (toggleLunes.isChecked || toggleMartes.isChecked || toggleMiercoles.isChecked || toggleJueves.isChecked || toggleViernes.isChecked || toggleSabado.isChecked || toggleDomingo.isChecked)
    }

    private fun obtenerDiasDeEntrenamiento() : MutableList<Boolean> {
        val diasDeEntrenamiento = mutableListOf<Boolean>()
        diasDeEntrenamiento.add(toggleLunes.isChecked)
        diasDeEntrenamiento.add(toggleMartes.isChecked)
        diasDeEntrenamiento.add(toggleMiercoles.isChecked)
        diasDeEntrenamiento.add(toggleJueves.isChecked)
        diasDeEntrenamiento.add(toggleViernes.isChecked)
        diasDeEntrenamiento.add(toggleSabado.isChecked)
        diasDeEntrenamiento.add(toggleDomingo.isChecked)
        return diasDeEntrenamiento
    }
}