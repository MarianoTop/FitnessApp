package com.example.fitnessapp.fragments

import android.content.ContentValues
import android.graphics.Color
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
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.entities.Usuario
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class PerfilHomeFragment : Fragment() {

    lateinit var v : View

    lateinit var buttonCerrarSesion : Button

    private lateinit var viewModel: PerfilHomeViewModel

    private val db = Firebase.firestore;
    lateinit var usuario : Usuario;
    lateinit var editTextNombre: EditText;
    lateinit var editTextPeso : EditText;
    lateinit var editTextAltura : EditText;
    lateinit var editTextEdad : EditText;
    lateinit var editTextDias : EditText;
    lateinit var editTextObjetivo : EditText;
    lateinit var editTextInforme : EditText;
    lateinit var editTextNivel : EditText;
    lateinit var editButton:  FloatingActionButton;
    var modoEdicion=false;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_perfil_home, container, false)

        buttonCerrarSesion = v.findViewById(R.id.btnCerrarSesion)

        buttonCerrarSesion.text = "Cerrar Sesion"
        editTextNombre = v.findViewById(R.id.editTextNombre);
        editTextPeso = v.findViewById(R.id.editTextPeso);
        editTextAltura = v.findViewById(R.id.editTextAltura);
        editTextEdad = v.findViewById(R.id.editTextEdad);
        editTextDias = v.findViewById(R.id.editTextDias);
        editTextObjetivo = v.findViewById(R.id.editTextObjetivo);
        editTextInforme = v.findViewById(R.id.editTextInformes);
        editTextNivel = v.findViewById(R.id.editTextNivel);
        editButton= v.findViewById(R.id.editButton)


        return v
    }

    override fun onStart() {
        super.onStart()

        cambiarEdit()
        val usuarioDB = db.collection("usuarios").document("ssoVgM3jDe2AenUf2xRd")
        usuarioDB.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(ContentValues.TAG, "DocumentSnapshot data: ${document.data}")
                    usuario = document.toObject()!!;
                    editTextNombre.setText(usuario.nombre, TextView.BufferType.EDITABLE);
                    editTextPeso.setText(usuario.peso.toString(), TextView.BufferType.EDITABLE);
                    editTextAltura.setText(usuario.altura.toString(), TextView.BufferType.EDITABLE);
                    editTextEdad.setText(usuario.edad.toString(), TextView.BufferType.EDITABLE);
                    editTextDias.setText(calcularCantidadDiasXSemana(usuario.diasDeEntrenamiento).toString(), TextView.BufferType.EDITABLE);
                    editTextObjetivo.setText(obtenerObjetivo(usuario.objetivo), TextView.BufferType.EDITABLE);
                    editTextInforme.setText(obtenerInforme(usuario.reporteMensual), TextView.BufferType.EDITABLE);
                    editTextNivel.setText(obtenerNivelFisico(usuario.nivelFisico), TextView.BufferType.EDITABLE);
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }


        editButton.setOnClickListener{

            cambiarEdit();
        }

        buttonCerrarSesion.setOnClickListener {
                val action = PerfilHomeFragmentDirections.actionPerfilHomeFragmentToMainActivity() // CAMBIAR
                findNavController().navigate(action)
        }
    }

    fun calcularCantidadDiasXSemana (semana : MutableList<Boolean>) : Int {
        var cantidadDias = 0;
        for (dia in semana){
            if(dia){
                cantidadDias++;
            }
        }
        return cantidadDias;
    }

    fun obtenerObjetivo (numero : Int) : String {
        var objetivo = "Bajar de peso";
        if (numero == 1){
            objetivo = "Aumentar masa muscular";
        }
        return objetivo;
    }

    fun obtenerInforme (numero : Int) : String {
        var informe = "Semanal";
        if(numero == 1){
            informe = "Mensual";
        }
        return informe;
    }

    fun obtenerNivelFisico(numero : Int) : String {
        var nivel = "Bajo";
        if(numero == 1){
            nivel = "Medio";
        }else if(numero == 2){
            nivel = "Alto";
        }
        return nivel;
    }

    fun cambiarEdit(){

        if(modoEdicion){

           /* https://stackoverflow.com/questions/45825609/programmatically-change-backgroundtint-of-imageview-with-vector-asset-for-backgr*/
            editButton.backgroundTintList=ContextCompat.getColorStateList(requireContext(), R.color.gray)
            modoEdicion=false
            cambiarCamposEditables()
            println("Se desactiva edicion")
        }else{
            editButton.backgroundTintList=ContextCompat.getColorStateList(requireContext(), R.color.red)
            modoEdicion=true
            cambiarCamposEditables()
            println("Se Habilita edicion")
        }

    }

    fun cambiarCamposEditables(){

       // editTextNombre.isFocusable = modoEdicion
        editTextPeso.isFocusable = modoEdicion
        editTextAltura.isFocusable = true
        /*

        editTextAltura = v.findViewById(R.id.editTextAltura);
        editTextEdad = v.findViewById(R.id.editTextEdad);
        editTextDias = v.findViewById(R.id.editTextDias);
        editTextObjetivo = v.findViewById(R.id.editTextObjetivo);
        editTextInforme = v.findViewById(R.id.editTextInformes);
        editTextNivel = v.findViewById(R.id.editTextNivel);
        editButton= v.findViewById(R.id.editButton)
        */

    }



}