package com.example.fitnessapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.adapters.SemanaAdapter
import com.example.fitnessapp.repository.SemanaRepository

class EntrenamientoHomeFragment : Fragment() {

    lateinit var v : View
    lateinit var  btnNavPrev : Button
    lateinit var recyclerView : RecyclerView;
    var semanaList : SemanaRepository = SemanaRepository();
    lateinit var semanaAdapter : SemanaAdapter;

    private lateinit var viewModel: EntrenamientoHomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_entrenamiento_home, container, false)
        btnNavPrev =v.findViewById(R.id.btnNavPrevisualization)
        recyclerView = v.findViewById(R.id.recyclerViewSemana)
        return v
    }

    override fun onStart() {
        super.onStart()
        semanaAdapter = SemanaAdapter(semanaList.semanas)
        recyclerView.layoutManager = LinearLayoutManager(context);
        recyclerView.adapter = semanaAdapter;
        btnNavPrev.setOnClickListener {



            val action =EntrenamientoHomeFragmentDirections.actionEntrenamientoHomeFragmentToPrevisualizacionEjercicioFragment()
            findNavController().navigate(action)


        }
    }

}