package com.example.fitnessapp.fragments.RecuperarContraseña

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitnessapp.R

class RecPasswordP3Fragment : Fragment() {

    lateinit var v : View

    private lateinit var viewModel: RecPasswordP3ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_rec_password_p3, container, false)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecPasswordP3ViewModel::class.java)
        // TODO: Use the ViewModel
    }

}