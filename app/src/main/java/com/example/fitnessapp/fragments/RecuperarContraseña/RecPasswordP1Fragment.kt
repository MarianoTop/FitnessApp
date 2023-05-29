package com.example.fitnessapp.fragments.RecuperarContraseña

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
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.fragments.Registro.SharedRegistrarseViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

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
            if(editTextEmail.text.toString().isEmpty()) {
                Toast.makeText(this.getContext(), "Debe completar el email.", Toast.LENGTH_SHORT,).show()
            } else {
            Firebase.auth.sendPasswordResetEmail(editTextEmail.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this.getContext(), "Se ha enviado un email al correo ingresado.", Toast.LENGTH_SHORT,).show()
                    } else {
                        /*Parece redundante, pero esta hecho asi porque sino daría un mensaje de error distinto cuando se ingresa un email no registrado, y eso
                        es un problema de seguridad ya que podriamos estar invitando a que por fuerza bruta puedan sacar todos los emails registrados de nuestra app o, como
                        minimo, que frente a un posible ataque, se aseguren si hay una cuenta hecha con un email o no.
                         */
                        Toast.makeText(this.getContext(), "Se ha enviado un email al correo ingresado.", Toast.LENGTH_SHORT,).show()
                    }
                }
            }
            //val action = RecPasswordP1FragmentDirections.actionRecPasswordP1FragmentToRecPasswordP2Fragment()
            //findNavController().navigate(action)


        }


    }

}