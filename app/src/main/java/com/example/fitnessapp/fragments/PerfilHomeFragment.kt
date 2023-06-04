package com.example.fitnessapp.fragments

import android.content.ContentValues
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.activities.MainActivity
import com.example.fitnessapp.entities.Usuario
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch


class PerfilHomeFragment : Fragment() ,AdapterView.OnItemSelectedListener {

    lateinit var v : View

    lateinit var buttonCerrarSesion : Button

    private val viewModel: PerfilHomeViewModel by activityViewModels()

    private val db = Firebase.firestore;
    lateinit var usuario : Usuario;
    lateinit var editTextNombre: EditText;
    lateinit var editTextPeso : EditText;
    lateinit var editTextAltura : EditText;
    lateinit var editTextEdad : EditText;
    lateinit var editTextDias : EditText;
    //lateinit var editTextObjetivo : EditText;
    lateinit var spinnerObjetivo : Spinner;
   //lateinit var editTextInforme : EditText;
   lateinit var spinnerInforme : Spinner;
    //lateinit var editTextNivel : EditText;
    lateinit var spinnerNivel : Spinner;

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
        //editTextObjetivo = v.findViewById(R.id.editTextObjetivo);
        /* Spinner
        https://developer.android.com/develop/ui/views/components/spinner
        https://www.youtube.com/watch?v=on_OrrX7Nw4&ab_channel=CodinginFlow*/
        spinnerObjetivo = v.findViewById(R.id.spinnerObj);
        //editTextInforme = v.findViewById(R.id.editTextInformes);
        spinnerInforme = v.findViewById(R.id.spinnerInform);
        //editTextNivel = v.findViewById(R.id.editTextNivel);
        spinnerNivel= v.findViewById(R.id.spinnerNivelFis);
        editButton= v.findViewById(R.id.editButton)


        val adapterObjective = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.objetivo, android.R.layout.simple_spinner_item
        )
        adapterObjective.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerObjetivo.adapter = adapterObjective
        spinnerObjetivo.onItemSelectedListener = this

        val adapterInforme = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.informe, android.R.layout.simple_spinner_item
        )
        adapterInforme.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerInforme.adapter = adapterInforme
        spinnerInforme.onItemSelectedListener = this


        val adapterNivelFisico = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.nivel, android.R.layout.simple_spinner_item
        )
        adapterNivelFisico.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerNivel.adapter = adapterNivelFisico
        spinnerNivel.onItemSelectedListener = this



        return v
    }

    override fun onStart() {
        super.onStart()


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
                    //editTextObjetivo.setText(obtenerObjetivo(usuario.objetivo), TextView.BufferType.EDITABLE);
                    spinnerObjetivo.setSelection(usuario.objetivo)
                    //editTextInforme.setText(obtenerInforme(usuario.reporteMensual), TextView.BufferType.EDITABLE);
                    spinnerInforme.setSelection(usuario.reporteSemanal)
                    //editTextNivel.setText(obtenerNivelFisico(usuario.nivelFisico), TextView.BufferType.EDITABLE);
                    spinnerNivel.setSelection(usuario.nivelFisico)
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
        cambiarCamposEditables()
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
            if(this.validarCamposDelPerfil()) {
                /* https://stackoverflow.com/questions/45825609/programmatically-change-backgroundtint-of-imageview-with-vector-asset-for-backgr*/
                editButton.backgroundTintList=ContextCompat.getColorStateList(requireContext(), R.color.gray)
                modoEdicion=false
                cambiarCamposEditables()
                obtenerDatos()
                viewModel.viewModelScope.launch {
                    viewModel.persistirUsuario(usuario)
                }
                println("Se desactiva edicion")
                this.buttonCerrarSesion.isEnabled = true;
                Toast.makeText(this.context, "Todo correcto", Toast.LENGTH_SHORT).show();
            }
        }else{
            editButton.backgroundTintList=ContextCompat.getColorStateList(requireContext(), R.color.red)
            modoEdicion=true
            cambiarCamposEditables()
            this.buttonCerrarSesion.isEnabled = false;
            println("Se Habilita edicion")
        }

    }

    fun cambiarCamposEditables(){

         /* https://stackoverflow.com/questions/7685280/edittext-how-to-enable-disable-input*/
        editTextNombre.isFocusable = modoEdicion
        editTextNombre.isFocusableInTouchMode = modoEdicion

        editTextPeso.isFocusable = modoEdicion
        editTextPeso.isFocusableInTouchMode = modoEdicion

        editTextAltura.isFocusable = modoEdicion
        editTextAltura.isFocusableInTouchMode = modoEdicion

        editTextEdad.isFocusable = modoEdicion
        editTextEdad.isFocusableInTouchMode = modoEdicion

        editTextDias.isFocusable = modoEdicion
        editTextDias.isFocusableInTouchMode = modoEdicion

        //editTextObjetivo.isFocusable = modoEdicion
        //editTextObjetivo.isFocusableInTouchMode = modoEdicion
        spinnerObjetivo.isEnabled=modoEdicion

       // editTextInforme.isFocusable = modoEdicion
        //editTextInforme.isFocusableInTouchMode = modoEdicion
        spinnerInforme.isEnabled=modoEdicion

        //editTextNivel.isFocusable = modoEdicion
        //editTextNivel.isFocusableInTouchMode = modoEdicion
        spinnerNivel.isEnabled=modoEdicion


    }

    fun obtenerDatos(){

        usuario.nombre=editTextNombre.text.toString()
        usuario.altura=editTextAltura.text.toString().toDouble()
        usuario.peso=editTextPeso.text.toString().toDouble()
        usuario.edad=editTextEdad.text.toString().toInt()
        usuario.objetivo=spinnerObjetivo.selectedItemPosition
        usuario.reporteSemanal=spinnerInforme.selectedItemPosition
        usuario.nivelFisico=spinnerNivel.selectedItemPosition

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        /* https://stackoverflow.com/questions/51839326/change-spinner-text-color*/
        /* https://stackoverflow.com/questions/15564760/change-the-text-not-background-color-of-a-spinner-when-an-item-is-selected*/
        /* https://stackoverflow.com/questions/37949024/kotlin-typecastexception-null-cannot-be-cast-to-non-null-type-com-midsizemango*/
        /* https://developer.android.com/codelabs/basic-android-kotlin-compose-nullability?hl=es-419#1*/
        var selectedText  =(parent.getChildAt(0) as? TextView)
        if (selectedText != null) {
            selectedText.setTextColor(Color.WHITE)
        }
        //val text: String = parent.getItemAtPosition(pos).toString()
        //Toast.makeText(parent.context, text, Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    //Validación de los campos
    private fun validarCamposDelPerfil(): Boolean {
        var esValido = true

        if (TextUtils.isEmpty(this.editTextNombre.text)) {
            // Si la propiedad error tiene valor, se muestra el aviso.
            this.editTextNombre.error = "Requerido"
            esValido = false
        } else this.editTextNombre.error = null

        /*
        if (TextUtils.isEmpty(binding.etPassword.text.toString())) {
            binding.etPassword.error = "Requerido"
            esValido = false
        } else binding.etPassword.error = null
        */
        return esValido
    }


}