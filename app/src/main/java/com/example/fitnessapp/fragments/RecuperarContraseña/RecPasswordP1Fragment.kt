package com.example.fitnessapp.fragments.RecuperarContraseña

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.fragments.Registro.SharedRegistrarseViewModel

class RecPasswordP1Fragment : Fragment() {

    lateinit var v : View

    private val sharedViewModel : SharedRecPasswordViewModel by activityViewModels()

    lateinit var textTitle : TextView
    lateinit var editTextEmail : EditText
    lateinit var buttonContinuar : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_rec_password_p1, container, false)

        textTitle = v.findViewById(R.id.textTitle3)
        editTextEmail = v.findViewById(R.id.editTextEmail1)
        buttonContinuar = v.findViewById(R.id.btnContinuar)

        textTitle.text = "Ingrese su email"
        buttonContinuar.text = "Continuar"

        return v
    }
    override fun onStart() {
        super.onStart()

        buttonContinuar.setOnClickListener {
            val action = RecPasswordP1FragmentDirections.actionRecPasswordP1FragmentToRecPasswordP2Fragment()
            findNavController().navigate(action)
        }


    }

}