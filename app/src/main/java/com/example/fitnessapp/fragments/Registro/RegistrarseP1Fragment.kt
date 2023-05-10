package com.example.fitnessapp.fragments.Registro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.fitnessapp.R

class RegistrarseP1Fragment : Fragment() {

    lateinit var v : View

    private val sharedViewModel : SharedRegistrarseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_registrarse_p1, container, false)



        return v
    }
}