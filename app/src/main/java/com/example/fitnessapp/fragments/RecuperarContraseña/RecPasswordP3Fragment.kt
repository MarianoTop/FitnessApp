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
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R

class RecPasswordP3Fragment : Fragment() {

    lateinit var v: View

    private lateinit var viewModel: RecPasswordP3ViewModel

    lateinit var textTitle: TextView
    lateinit var editTextPassword: EditText
    lateinit var editTextRepeatPassword: EditText
    lateinit var buttonCambiarContraseña: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_rec_password_p3, container, false)

        textTitle = v.findViewById(R.id.textTitle7)
        editTextPassword = v.findViewById(R.id.editTextPassword)
        editTextRepeatPassword = v.findViewById(R.id.editTextRepeatPassword)
        buttonCambiarContraseña = v.findViewById(R.id.btnCambiarContraseña)

        textTitle.text = "Ingrese su nueva contraseña"
        buttonCambiarContraseña.text = "Cambiar contraseña"

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecPasswordP3ViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        buttonCambiarContraseña.setOnClickListener {
            val action =
                RecPasswordP3FragmentDirections.actionRecPasswordP3FragmentToIniciarSesionFragment()
            findNavController().navigate(action)
        }


    }
}