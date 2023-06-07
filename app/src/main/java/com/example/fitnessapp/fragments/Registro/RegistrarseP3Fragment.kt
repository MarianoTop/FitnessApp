package com.example.fitnessapp.fragments.Registro

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R
import java.util.regex.Pattern

class RegistrarseP3Fragment : Fragment() {

    lateinit var v : View

    private val sharedViewModel : SharedRegistrarseViewModel by activityViewModels()
    lateinit private var textNombre : EditText
    lateinit private var textPeso : EditText
    lateinit private var textAltura : EditText
    lateinit private var textEdad : EditText
    lateinit private var btnSiguiente : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_registrarse_p3, container, false)
        textNombre = v.findViewById(R.id.textNombre3)
        textPeso = v.findViewById(R.id.textPeso3)
        textAltura = v.findViewById(R.id.textAltura3)
        textEdad = v.findViewById(R.id.textEdad3)
        btnSiguiente = v.findViewById(R.id.btnSiguiente2)

        return v
    }

    override fun onStart() {
        super.onStart()

        btnSiguiente.setOnClickListener {
            if(validarCamposDelPerfil()) {
                sharedViewModel.usuario.nombre = textNombre.text.toString()
                sharedViewModel.usuario.altura = textAltura.text.toString().toDouble()
                sharedViewModel.usuario.peso = textPeso.text.toString().toDouble()
                sharedViewModel.usuario.edad = textEdad.text.toString().toInt()
                val action = RegistrarseP3FragmentDirections.actionRegistrarseP3FragmentToRegistrarseP4Fragment()
                findNavController().navigate(action)
            }
        }
    }

    private fun validarCamposDelPerfil(): Boolean {
        var esValido = true
        //Validación campo vacío
        val nombre : String = this.textNombre.text.toString();
        if (TextUtils.isEmpty(nombre)) {
            // Si la propiedad error tiene valor, se muestra el aviso.
            this.textNombre.error = "Requerido"
            esValido = false
        }else if(nombre.length < 3 || nombre.length > 10) {
            this.textNombre.error = "Entre 3 y 10 caracteres";
            esValido = false;
        }else if(!Pattern.compile("^[a-zA-Z ]+\$").matcher(nombre).matches()){
            this.textNombre.error = "Caracteres invalidos";
            esValido = false;
        }
        else this.textNombre.error = null

        //Validación campo vacío
        val peso : String = this.textPeso.text.toString();
        if (TextUtils.isEmpty(peso)) {
            this.textPeso.error = "Requerido"
            esValido = false
        }else if(peso.toDouble() < 25 || peso.toDouble()> 180){
            this.textPeso.error = "Peso invalido";
            esValido = false;
        }
        else this.textPeso.error = null

        //Validación campo vacío
        val altura : String = this.textAltura.text.toString();
        if (TextUtils.isEmpty(altura)) {
            this.textAltura.error = "Requerido"
            esValido = false
        }else if(altura.toDouble() < 1.20 || altura.toDouble() > 2.40){
            this.textAltura.error = "Altura invalida";
            esValido = false;
        } else this.textAltura.error = null

        //Validación campo vacío
        val edad : String = this.textEdad.text.toString();
        if (TextUtils.isEmpty(edad)){
            this.textEdad.error = "Requerido";
            esValido = false
            //Validación 0 > edad < 100
        } else if(Integer.parseInt(edad) < 1 || Integer.parseInt(edad) > 99){
            this.textEdad.error = "Edad invalida";
            esValido = false
        }
        else this.textEdad.error = null

        return esValido
    }
}