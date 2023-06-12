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

class RegistrarseP5Fragment : Fragment() {

    lateinit var v : View

    private val sharedViewModel : SharedRegistrarseViewModel by activityViewModels()
    lateinit private var btnNivelBajo : Button
    lateinit private var btnNivelMedio : Button
    lateinit private var btnNivelAlto : Button
    lateinit private var btnSemanal : Button
    lateinit private var btnMensual : Button
    lateinit private var btnFinalizar : Button
    private var nivel : Int = -1
    private var reporteSemanal: Int = 0
    private var reporteMensual: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_registrarse_p5, container, false)
        btnNivelBajo = v.findViewById(R.id.btnNivelBajo)
        btnNivelMedio = v.findViewById(R.id.btnNivelMedio)
        btnNivelAlto = v.findViewById(R.id.btnNivelAlto)
        btnSemanal = v.findViewById(R.id.btnInformesSemanales)
        btnMensual = v.findViewById(R.id.btnInformesMensuales)
        btnFinalizar = v.findViewById(R.id.btnSiguiente3)
        return v
    }

    override fun onStart() {
        super.onStart()
        btnNivelBajo.setOnClickListener {
            nivel = 0
            btnNivelBajo.setBackgroundColor(0xff960000.toInt())
            btnNivelMedio.setBackgroundColor(0xff969695.toInt())
            btnNivelAlto.setBackgroundColor(0xff969695.toInt())
        }
        btnNivelMedio.setOnClickListener {
            nivel = 1
            btnNivelBajo.setBackgroundColor(0xff969695.toInt())
            btnNivelMedio.setBackgroundColor(0xff960000.toInt())
            btnNivelAlto.setBackgroundColor(0xff969695.toInt())
        }
        btnNivelAlto.setOnClickListener {
            nivel = 2
            btnNivelBajo.setBackgroundColor(0xff969695.toInt())
            btnNivelMedio.setBackgroundColor(0xff969695.toInt())
            btnNivelAlto.setBackgroundColor(0xff960000.toInt())
        }
        btnSemanal.setOnClickListener {
            reporteSemanal = 1
            reporteMensual = 0
            btnMensual.setBackgroundColor(0xff969695.toInt())
            btnSemanal.setBackgroundColor(0xff960000.toInt())
        }
        btnMensual.setOnClickListener {
            reporteSemanal = 0
            reporteMensual = 1
            btnSemanal.setBackgroundColor(0xff969695.toInt())
            btnMensual.setBackgroundColor(0xff960000.toInt())
        }
        btnFinalizar.setOnClickListener {
            if(nivel == -1)
            {
                Toast.makeText(context, "Debe seleccionar su nivel fisico", Toast.LENGTH_SHORT,).show()
            } else if (reporteSemanal == 0 && reporteMensual == 0)
            {
                Toast.makeText(context, "Debe elegir que reporte desea", Toast.LENGTH_SHORT,).show()
            }
            else
            {
                sharedViewModel.usuario.nivelFisico = nivel
                sharedViewModel.usuario.reporteSemanal = reporteSemanal
                sharedViewModel.usuario.reporteMensual = reporteMensual
                sharedViewModel.viewModelScope.launch {
                    if(sharedViewModel.persistirUsuario(sharedViewModel.usuario)) {
                        val action = RegistrarseP5FragmentDirections.actionRegistrarseP5FragmentToMainActivity2()
                        findNavController().navigate(action)
                    }
                    else {
                        Toast.makeText(context, "Ocurrio un error completando el perfil.", Toast.LENGTH_SHORT,).show()
                    }
                }
            }
        }
    }
}