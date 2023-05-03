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

class RecPasswordP2Fragment : Fragment() {

    lateinit var v : View

    private lateinit var viewModel: RecPasswordP2ViewModel

    lateinit var textTitle : TextView
    lateinit var editTextEmail : EditText
    lateinit var buttonContinuar : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_rec_password_p2, container, false)

        textTitle = v.findViewById(R.id.textTitle4)
        editTextEmail = v.findViewById(R.id.editTextCodeEmail)
        buttonContinuar = v.findViewById(R.id.btnContinuar1)

        textTitle.text = "Ingrese el codigo enviado a su email"
        buttonContinuar.text = "Cambiar contraseña"

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecPasswordP2ViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        buttonContinuar.setOnClickListener {
            val action = RecPasswordP2FragmentDirections.actionRecPasswordP2FragmentToRecPasswordP3Fragment()
            findNavController().navigate(action)
        }


    }
}