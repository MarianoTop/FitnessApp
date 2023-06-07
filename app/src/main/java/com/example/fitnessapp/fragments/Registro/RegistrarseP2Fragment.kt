package com.example.fitnessapp.fragments.Registro

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R
import kotlinx.coroutines.launch

class RegistrarseP2Fragment : Fragment() {

    lateinit var v : View
    lateinit var btnMasculino : Button
    lateinit var btnFemenino : Button
    lateinit var btnOtro : Button

    private val sharedViewModel : SharedRegistrarseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_registrarse_p2, container, false)
        btnMasculino = v.findViewById(R.id.btnMasculino)
        btnFemenino = v.findViewById(R.id.btnFemenino)
        btnOtro = v.findViewById(R.id.btnOtro)

        return v
    }

    override fun onStart() {
        super.onStart()

        btnMasculino.setOnClickListener {
            sharedViewModel.usuario.genero = "Masculino"
            val action =
                RegistrarseP2FragmentDirections.actionRegistrarseP2FragmentToRegistrarseP3Fragment()
            findNavController().navigate(action)
        }
        btnFemenino.setOnClickListener {
            sharedViewModel.usuario.genero = "Femenino"
            val action =
                RegistrarseP2FragmentDirections.actionRegistrarseP2FragmentToRegistrarseP3Fragment()
            findNavController().navigate(action)
        }
        btnOtro.setOnClickListener {
            sharedViewModel.usuario.genero = "Otro"
            val action =
                RegistrarseP2FragmentDirections.actionRegistrarseP2FragmentToRegistrarseP3Fragment()
            findNavController().navigate(action)
        }
    }
}