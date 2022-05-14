package com.example.cleanwater_project.presentation.total

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cleanwater_project.R
import com.google.android.material.button.MaterialButton

class CheckResearchFragment : Fragment(R.layout.check_research_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        view.findViewById<MaterialButton>(R.id.continue_button).setOnClickListener {
            findNavController().navigate(R.id.action_checkResearchFragment_to_totalFragment)
        }
    }

}