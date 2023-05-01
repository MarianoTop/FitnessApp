package com.example.fitnessapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.adapters.RutinaAdapter
import com.example.fitnessapp.adapters.SemanaAdapter
import com.example.fitnessapp.entities.Rutina
import com.example.fitnessapp.repository.SemanaRepository
import com.google.android.material.snackbar.Snackbar

class RutinaFragment : Fragment() {

    lateinit var v : View;
    lateinit var recyclerView : RecyclerView;
    lateinit var rutinaList : MutableList<Rutina>;
    lateinit var rutinaAdapter : RutinaAdapter;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_rutina, container, false)
        recyclerView = v.findViewById(R.id.recyclerViewRutina)
        rutinaList = RutinaFragmentArgs.fromBundle(requireArguments()).SemanaEntity.rutinas
        return v;
    }

    override fun onStart() {
        super.onStart()
        rutinaAdapter = RutinaAdapter(rutinaList){position ->
            //Snackbar.make(v, "Click en ${rutinaList[position].id}", Snackbar.LENGTH_SHORT).show();
            val action = RutinaFragmentDirections.actionRutinaFragmentToPrevisualizacionEjercicioFragment(rutinaList[position])
            findNavController().navigate(action)
        }

        recyclerView.layoutManager = LinearLayoutManager(context);
        recyclerView.adapter = rutinaAdapter;
    }

}