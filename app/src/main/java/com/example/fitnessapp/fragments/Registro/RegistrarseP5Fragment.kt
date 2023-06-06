package com.example.fitnessapp.fragments.Registro

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R

class RegistrarseP5Fragment : Fragment() {

    lateinit var v : View

    private val sharedViewModel : SharedRegistrarseViewModel by activityViewModels()

    lateinit var btnSig: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_registrarse_p5, container, false)

        btnSig = v.findViewById(R.id.btnSiguiente3)

        return v
    }

    override fun onStart() {
        super.onStart()

        btnSig.setOnClickListener {
            val action =
                RegistrarseP5FragmentDirections.actionRegistrarseP5FragmentToMainActivity2()
            findNavController().navigate(action)
        }
    }
}