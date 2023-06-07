package com.example.fitnessapp.fragments

import android.content.ContentValues
import android.content.ContentValues.TAG
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.entities.Ejercicio
import com.example.fitnessapp.entities.Rutina
import com.example.fitnessapp.entities.Usuario
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

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
            if(editTextEmail.text.toString().isEmpty()) {
                Toast.makeText( this.getContext(), "Debe completar el email.", Toast.LENGTH_SHORT,).show()
            } else if (editTextContraseña.text.toString().isEmpty()) {
                Toast.makeText( this.getContext(), "Debe introducir la contraseña.", Toast.LENGTH_SHORT,).show()
            }
            else {
                viewModel.obtenerUsuario(editTextEmail.text.toString(), editTextContraseña.text.toString())
                viewModel.usuario.observe(viewLifecycleOwner, Observer { usuario: Usuario? ->
                    println("----- usuario coso: " + usuario)
                    if (usuario != null) {
                        val action =
                            IniciarSesionFragmentDirections.actionIniciarSesionFragmentToMainActivity2()
                        findNavController().navigate(action)
                    } else {

                        Snackbar.make(v, "Usuario o contraseña incorrectos.", Snackbar.LENGTH_SHORT).show();
                    }
                })
            }
        }

        buttonRecuperarContraseña.setOnClickListener {
            val action = IniciarSesionFragmentDirections.actionIniciarSesionFragmentToRecPasswordFragment()
            findNavController().navigate(action)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IniciarSesionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}