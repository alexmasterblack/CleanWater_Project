package com.example.cleanwater_project.presentation.start

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cleanwater_project.R

class RegistrationFragment : Fragment(R.layout.registration_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        view.findViewById<TextView>(R.id.registration_button).setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_versionSelectionFragment)
        }
    }
}