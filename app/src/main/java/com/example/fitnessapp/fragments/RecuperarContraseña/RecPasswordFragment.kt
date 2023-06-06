package com.example.fitnessapp.fragments.RecuperarContraseña

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.fitnessapp.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RecPasswordFragment : Fragment() {

    lateinit var v : View

    private val sharedViewModel : SharedRecPasswordViewModel by activityViewModels()

    lateinit var textTitle : TextView
    lateinit var editTextEmail : EditText
    lateinit var buttonContinuar : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_rec_password, container, false)

        textTitle = v.findViewById(R.id.textTitle3)
        editTextEmail = v.findViewById(R.id.editTextEmail1)
        buttonContinuar = v.findViewById(R.id.btnContinuar)

        textTitle.text = "Ingrese su email"
        buttonContinuar.text = "Cambiar contraseña"

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
            // val action = RecPasswordFragmentDirections.actionRecPasswordFragmentToIniciarSesionFragment()
            // findNavController().navigate(action)
        }
    }
}