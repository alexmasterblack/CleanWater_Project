package com.example.cleanwater_project.presentation.rest

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.cleanwater_project.R
import com.example.cleanwater_project.presentation.adapters.ViewPagerAdapter
import com.google.android.material.snackbar.Snackbar
import me.relex.circleindicator.CircleIndicator3

class VersionSelectionFragment : Fragment(R.layout.version_selection_fragment) {

    private val tables =
        mutableListOf(R.drawable.ic_table_benefits_1, R.drawable.ic_table_benefits_2)

    private fun setViewPagerAdapter(view: View) {
        val pager = view.findViewById<ViewPager2>(R.id.pager)
        val adapter = ViewPagerAdapter(tables)
        pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        pager.adapter = adapter

        val indicator = view.findViewById<CircleIndicator3>(R.id.indicator)
        indicator.setViewPager(pager)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.no_buy_button).setOnClickListener {
            findNavController().navigate(R.id.action_versionSelectionFragment_to_mainFragment)
        }

        view.findViewById<TextView>(R.id.buy_button).setOnClickListener {
            val snackbar = Snackbar.make(
                this.requireView(),
                R.string.snackbar_buy,
                Snackbar.LENGTH_LONG
            )
            snackbar.setAction(R.string.snackbar_clear) {
                snackbar.dismiss()
            }
            snackbar.show()
        }

        setViewPagerAdapter(view)
    }
}