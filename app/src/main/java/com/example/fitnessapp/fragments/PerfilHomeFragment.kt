package com.example.fitnessapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R

class PerfilHomeFragment : Fragment() {

    lateinit var v : View

    lateinit var buttonCerrarSesion : Button

    private lateinit var viewModel: PerfilHomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_perfil_home, container, false)

        buttonCerrarSesion = v.findViewById(R.id.btnCerrarSesion)

        buttonCerrarSesion.text = "Cerrar Sesion"

        return v
    }

    override fun onStart() {
        super.onStart()

        buttonCerrarSesion.setOnClickListener {
                val action = PerfilHomeFragmentDirections.actionPerfilHomeFragmentToMainActivity() // CAMBIAR
                findNavController().navigate(action)
        }
    }

}