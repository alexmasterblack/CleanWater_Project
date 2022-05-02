package com.example.cleanwater_project.presentation.rest

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cleanwater_project.R

class VersionSelectionFragment : Fragment(R.layout.version_selection_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.no_buy_button).setOnClickListener {
            findNavController().navigate(R.id.action_versionSelectionFragment_to_mainFragment)
        }
    }
}