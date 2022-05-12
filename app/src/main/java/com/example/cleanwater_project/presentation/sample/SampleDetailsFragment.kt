package com.example.cleanwater_project.presentation.sample

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cleanwater_project.R

class SampleDetailsFragment : Fragment(R.layout.sample_details_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}