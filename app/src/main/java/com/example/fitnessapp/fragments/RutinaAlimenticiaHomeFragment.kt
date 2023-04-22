package com.example.fitnessapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitnessapp.R

class RutinaAlimenticiaHomeFragment : Fragment() {

    lateinit var v : View

    private lateinit var viewModel: RutinaAlimenticiaHomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_rutina_alimenticia_home, container, false)
        return v
    }

}