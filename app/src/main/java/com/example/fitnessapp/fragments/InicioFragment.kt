package com.example.fitnessapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R

class InicioFragment : Fragment() {

    lateinit var v : View

    private lateinit var viewModel: InicioFragmentViewModel

    lateinit var buttonIniciarSesion : Button
    lateinit var buttonRegistrarse : Button
    lateinit var textRegistro : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_inicio_fragment, container, false)

        buttonIniciarSesion = v.findViewById(R.id.btnIniciarSesion)
        buttonRegistrarse = v.findViewById(R.id.btnRegistrarse)
        textRegistro = v.findViewById(R.id.textRegistro)

        buttonIniciarSesion.text = "Iniciar Sesion"
        buttonRegistrarse.text = "Registrarse"
        textRegistro.text = "No tenes una cuenta?"

        return v
    }

    override fun onStart() {
        super.onStart()

        buttonIniciarSesion.setOnClickListener {
            val action = InicioFragmentDirections.actionInicioFragmentToIniciarSesionFragment()
            findNavController().navigate(action)
        }

        buttonRegistrarse.setOnClickListener {
            val action = InicioFragmentDirections.actionInicioFragmentToRegistrarseP1Fragment()
            findNavController().navigate(action)
        }

    }

}