package com.example.cleanwater_project.presentation.sample

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.cleanwater_project.R
import com.example.cleanwater_project.presentation.adapters.ViewPagerAdapter
import com.google.android.material.slider.Slider
import me.relex.circleindicator.CircleIndicator3

class SampleDetailsFragment : Fragment(R.layout.sample_details_fragment) {

    private val tables =
        mutableListOf(R.mipmap.ephemeroptera_1, R.mipmap.ephemeroptera_2)

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

        view.findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        setViewPagerAdapter(view)

        val slider = view.findViewById<Slider>(R.id.slider)
        slider.addOnChangeListener { slider, value, fromUser ->
            view.findViewById<TextView>(R.id.amount).text = value.toInt().toString()
        }

        view.findViewById<ImageView>(R.id.minus).setOnClickListener {
            if (slider.value.toInt() != 0) {
                slider.value -= 1
            }
        }

        view.findViewById<ImageView>(R.id.plus).setOnClickListener {
            if (slider.value.toInt() != 50) {
                slider.value += 1
            }
        }

    }
}