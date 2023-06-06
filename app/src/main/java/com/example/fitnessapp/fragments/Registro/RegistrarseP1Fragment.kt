package com.example.fitnessapp.fragments.Registro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.fragments.IniciarSesionFragmentDirections

class RegistrarseP1Fragment : Fragment() {

    lateinit var v : View

    private val sharedViewModel : SharedRegistrarseViewModel by activityViewModels()

    lateinit var btnIniciarSesion : Button
    lateinit var btnSig : Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_registrarse_p1, container, false)

        btnIniciarSesion = v.findViewById(R.id.btnIniciarSesion2)
        btnSig = v.findViewById(R.id.btnContinuar2)

        return v
    }

    override fun onStart() {
        super.onStart()

        btnIniciarSesion.setOnClickListener {
            val action = RegistrarseP1FragmentDirections.actionRegistrarseP1FragmentToIniciarSesionFragment()
            findNavController().navigate(action)
        }

        btnSig.setOnClickListener {
            val action = RegistrarseP1FragmentDirections.actionRegistrarseP1FragmentToRegistrarseP2Fragment()
            findNavController().navigate(action)
        }
    }
}