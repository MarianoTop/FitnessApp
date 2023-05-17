package com.example.fitnessapp.fragments.NutricionComidas

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.adapters.ComidaAdapter
import com.example.fitnessapp.entities.Comida
import com.example.fitnessapp.entities.ComidasRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListaComidasTipoFragment : Fragment() {

    lateinit var v : View

    lateinit var textTipoComida : TextView

    lateinit var recyclerComidas : RecyclerView
    var repository : ComidasRepository = ComidasRepository()

    lateinit var adapter : ComidaAdapter

    private lateinit var viewModel : ListaComidasTipoViewModel

    val db = Firebase.firestore

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

        lateinit var contextSent : Context
        if(context!=null){
            contextSent= context as Context
        }

        adapter = ComidaAdapter(contextSent, repository.comidas){ position ->

            val action = ListaComidasTipoFragmentDirections.actionListaComidasTipoFragmentToComidaDetalladaFragment(repository.comidas[position])
            findNavController().navigate(action)

            //Snackbar.make(v,"Click en ${repository.facturas[position].name}", Snackbar.LENGTH_LONG).show()
        }
        recyclerComidas.layoutManager = LinearLayoutManager(context)
        recyclerComidas.adapter = adapter

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListaComidasTipoViewModel::class.java)
    }

}