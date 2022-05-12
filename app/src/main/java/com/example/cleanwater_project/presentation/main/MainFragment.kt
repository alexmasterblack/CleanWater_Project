package com.example.cleanwater_project.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.cleanwater_project.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment : Fragment(R.layout.main_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavView = view.findViewById<BottomNavigationView>(R.id.main_navigation)
        val navController =
            (childFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment).navController

        // потом сделать по-человечески
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val fragments =
                intArrayOf(
                    R.id.sampleFragment,
                    R.id.checkResearchFragment,
                    R.id.totalFragment,
                    R.id.researchDetailsFragment,
                    R.id.sampleDetailsFragment
                )
            if (destination.id in fragments) {

                bottomNavView.visibility = View.GONE
            } else {

                bottomNavView.visibility = View.VISIBLE
            }
        }

        bottomNavView.setupWithNavController(navController)
    }
}