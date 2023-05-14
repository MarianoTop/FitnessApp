package com.example.fitnessapp.fragments.NutricionComidas

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitnessapp.R

class MeriendasFragment : Fragment() {

    lateinit var v : View

    private lateinit var viewModel: MeriendasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_meriendas, container, false)

        // RECYCLERVIEW - CARDS

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MeriendasViewModel::class.java)
        // TODO: Use the ViewModel
    }

}