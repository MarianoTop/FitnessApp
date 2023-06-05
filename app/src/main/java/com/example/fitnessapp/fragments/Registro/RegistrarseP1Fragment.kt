package com.example.fitnessapp.fragments.Registro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.entities.Usuario
import com.example.fitnessapp.fragments.Ejercicio.SharedViewModel
import com.example.fitnessapp.fragments.IniciarSesionFragmentDirections
import kotlinx.coroutines.launch

class RegistrarseP1Fragment : Fragment() {

    lateinit var v : View

    private val sharedViewModel : SharedRegistrarseViewModel by activityViewModels()

    lateinit var editTextMail: EditText
    lateinit var editTextPw1: EditText
    lateinit var editTextPw2: EditText
    lateinit var btnContinuar: Button
    lateinit var btnIniciarSesion : Button



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_registrarse_p1, container, false)
        editTextMail = v.findViewById(R.id.editTextEmail3)
        editTextPw1 = v.findViewById(R.id.editTextContraseña2)
        editTextPw2 = v.findViewById(R.id.editTextContraseña3)
        btnContinuar = v.findViewById(R.id.btnContinuar2)
        btnIniciarSesion = v.findViewById(R.id.btnIniciarSesion2)

        return v
    }

    override fun onStart() {
        super.onStart()

        btnContinuar.setOnClickListener {
            if(editTextMail.text.length == 0 || !editTextMail.text.contains("@")) {
                Toast.makeText(this.getContext(), "Debe ingresar un email valido.", Toast.LENGTH_SHORT,).show()
            }
            else {
                if(editTextPw1.text.toString() == editTextPw2.text.toString()) {
                    var usuarioCreado : Boolean
                    sharedViewModel.viewModelScope.launch {
                        usuarioCreado = sharedViewModel.crearUsuario(editTextMail.text.toString(), editTextPw1.text.toString())
                        if(usuarioCreado) {
                            val action = RegistrarseP1FragmentDirections.actionRegistrarseP1FragmentToRegistrarseP2Fragment()
                            findNavController().navigate(action)
                        } else {
                            Toast.makeText(context, "Ocurrio un error creando la cuenta.", Toast.LENGTH_SHORT,).show()
                        }
                    }
                }
                else {
                    Toast.makeText(this.getContext(), "Las contraseñas ingresadas no coinciden.", Toast.LENGTH_SHORT,).show()
                }
            }
        }
    }
}