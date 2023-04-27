package com.example.fitnessapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R

class LogInFragment1 : Fragment() {

    lateinit var v : View

    private lateinit var viewModel: LogInFragment1ViewModel

    lateinit var button : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_log_in_fragment1, container, false)

        button = v.findViewById(R.id.button2)

        button.text = "Inciar Sesion"

        return v
    }

    override fun onStart() {
        super.onStart()

        button.setOnClickListener {
            val action = LogInFragment1Directions.actionLogInFragment1ToMainActivity2()
            findNavController().navigate(action)
        }

    }

}