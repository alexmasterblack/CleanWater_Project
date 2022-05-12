package com.example.cleanwater_project.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cleanwater_project.R
import com.google.android.material.button.MaterialButton

class InputResearchFragment : Fragment(R.layout.input_research_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<MaterialButton>(R.id.buy_button).setOnClickListener {
            findNavController().navigate(R.id.action_inputResearchFragment_to_sampleFragment)
        }
    }
}