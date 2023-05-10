package com.example.fitnessapp.fragments

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

class IniciarSesionFragment : Fragment() {

    lateinit var v : View

    private lateinit var viewModel: IniciarSesionViewModel

    lateinit var buttonIngresar : Button
    lateinit var buttonRegistrarse : Button
    lateinit var buttonRecuperarContraseña : Button
    lateinit var textRecuperarContraseña : TextView
    lateinit var textBienvenida : TextView
    lateinit var editTextEmail : EditText
    lateinit var editTextContraseña : EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_iniciar_sesion, container, false)

        buttonRegistrarse = v.findViewById(R.id.btnRegistrarse1)
        buttonRecuperarContraseña = v.findViewById(R.id.btnRecuperarContraseña)
        buttonIngresar = v.findViewById(R.id.btnIngresar)
        textBienvenida = v.findViewById(R.id.textBienvenida)
        textRecuperarContraseña = v.findViewById(R.id.textRecuperarContraseña)
        editTextEmail = v.findViewById(R.id.editTextEmail)
        editTextContraseña = v.findViewById(R.id.editTextContraseña)

        buttonRegistrarse.text = "Registrarse"
        buttonRecuperarContraseña.text = "Recuperar"
        buttonIngresar.text = "Continuar"
        textBienvenida.text = "Bienvenid@ a FitCia"
        textRecuperarContraseña.text = "Olvidaste la contraseña?"

        return v
    }

    override fun onStart() {
        super.onStart()

        buttonRegistrarse.setOnClickListener {
            val action = IniciarSesionFragmentDirections.actionIniciarSesionFragmentToRegistrarseP1Fragment()
            findNavController().navigate(action)
        }

        buttonIngresar.setOnClickListener {
            val action = IniciarSesionFragmentDirections.actionIniciarSesionFragmentToMainActivity2()
            findNavController().navigate(action)
        }

        buttonRecuperarContraseña.setOnClickListener {
            val action = IniciarSesionFragmentDirections.actionIniciarSesionFragmentToRecPasswordP1Fragment()
            findNavController().navigate(action)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IniciarSesionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}