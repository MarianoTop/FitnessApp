package com.example.fitnessapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitnessapp.R

class PrevisualizacionEjercicioFragment : Fragment() {

    companion object {
        fun newInstance() = PrevisualizacionEjercicioFragment()
    }

    private lateinit var viewModel: PrevisualizacionEjercicioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_previsualizacion_ejercicio, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PrevisualizacionEjercicioViewModel::class.java)
        // TODO: Use the ViewModel
    }

}