package com.example.fitnessapp.fragments.NutricionComidas

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.adapters.ComidaAdapter
import com.example.fitnessapp.entities.Comida
import com.example.fitnessapp.entities.ComidasRepository
import com.example.fitnessapp.fragments.NutricionViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListaComidasTipoFragment : Fragment() {

    lateinit var v : View

    private val sharedViewModel : NutricionViewModel by activityViewModels()

    lateinit var textTipoComida : TextView

    lateinit var recyclerComidas : RecyclerView

    var repository : ComidasRepository = ComidasRepository()

    lateinit var adapter : ComidaAdapter

    lateinit var lista : MutableList<Comida>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_lista_comidas_tipo, container, false)

        textTipoComida = v.findViewById(R.id.textTipoComida)
        recyclerComidas = v.findViewById(R.id.recyclerComidas)

        return v
    }

    override fun onStart() {
        super.onStart()

        textTipoComida.text = ListaComidasTipoFragmentArgs.fromBundle(requireArguments()).titulo
        var tipo = ListaComidasTipoFragmentArgs.fromBundle(requireArguments()).tipo
/*
        if (tipo == 0) {
            lista = sharedViewModel.comidasDesayunoMerienda
        } else {
            lista = sharedViewModel.comidasAlmuerzoCena
        }

 */

        lateinit var contextSent : Context
        if(context!=null){
            contextSent= context as Context
        }

        adapter = ComidaAdapter(contextSent, repository.comidas){ position ->

            val action = ListaComidasTipoFragmentDirections.actionListaComidasTipoFragmentToComidaDetalladaFragment(repository.comidas[position])
            findNavController().navigate(action)

            //Snackbar.make(v,"Click en ${repository.comidas[position].nombre}", Snackbar.LENGTH_LONG).show()
        }
        recyclerComidas.layoutManager = LinearLayoutManager(context)
        recyclerComidas.adapter = adapter

    }
}