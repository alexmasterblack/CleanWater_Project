package com.example.cleanwater_project.presentation.sample

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.cleanwater_project.R
import com.example.cleanwater_project.presentation.adapters.ViewPagerAdapter
import com.example.data.hydrobionts.entities.Hydrobiont
import com.example.data.probe.entities.Probe
import com.example.data.repository.Repositories
import com.google.android.material.slider.Slider
import kotlinx.coroutines.*
import me.relex.circleindicator.CircleIndicator3

class SampleDetailsFragment : Fragment(R.layout.sample_details_fragment) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val args by navArgs<SampleDetailsFragmentArgs>()

    private val hydrobiontId by lazy { args.hydrobiontId }

    private val researchId by lazy { args.researchId }

    private val amount by lazy { args.amount }

    private val data = MutableLiveData<Hydrobiont>()

    private val tables = mutableListOf<Int>()

    override fun onPause() {
        super.onPause()
        coroutineScope.cancel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            coroutineScope.launch {
                Repositories.probeRepository.updateProbeAmount(
                    Probe(
                        researchId,
                        hydrobiontId,
                        view.findViewById<Slider>(R.id.slider).value.toInt(),
                        0.0
                    )
                )
            }

            findNavController().navigateUp()
        }

        coroutineScope.launch {
            Repositories.hydrobiontRepository.getHydrobiont(hydrobiontId).collect {
                data.postValue(it)
            }
        }

        data.observe(requireActivity()) {
            setHydrobiontInfo(view, it)
        }

        setImages()

        setViewPagerAdapter(view)

        setSlider(view)
    }

    private fun setSlider(view: View) {
        val slider = view.findViewById<Slider>(R.id.slider)
        slider.addOnChangeListener { _, value, _ ->
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

    private fun setViewPagerAdapter(view: View) {
        val pager = view.findViewById<ViewPager2>(R.id.pager)
        val adapter = ViewPagerAdapter()
        pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        pager.adapter = adapter

        adapter.setData(tables)

        if (tables.size > 1) {
            val indicator = view.findViewById<CircleIndicator3>(R.id.indicator)
            indicator.setViewPager(pager)
        }
    }

    private fun setHydrobiontInfo(view: View, hydrobiont: Hydrobiont) {
        view.findViewById<TextView>(R.id.hydrobiont_name).text =
            hydrobiont.name + " (" + hydrobiont.latinName + ")"
        view.findViewById<TextView>(R.id.information).text = hydrobiont.description
        view.findViewById<Slider>(R.id.slider).value = amount.toFloat()
    }

    private fun setImages() {
        if (hydrobiontId.toInt() == 1) {
            tables.add(R.mipmap.trichoptera_1)
            tables.add(R.mipmap.trichoptera_2)
            tables.add(R.mipmap.trichoptera_3)
            tables.add(R.mipmap.trichoptera_4)
        } else if (hydrobiontId.toInt() == 2) {
            tables.add(R.mipmap.plecoptera_1)
            tables.add(R.mipmap.plecoptera_2)
            tables.add(R.mipmap.plecoptera_3)
        } else if (hydrobiontId.toInt() == 3) {
            tables.add(R.mipmap.ephemeroptera_1)
            tables.add(R.mipmap.ephemeroptera_2)
            tables.add(R.mipmap.ephemeroptera_3)
            tables.add(R.mipmap.ephemeroptera_4)
            tables.add(R.mipmap.ephemeroptera_5)
            tables.add(R.mipmap.ephemeroptera_6)
        } else if (hydrobiontId.toInt() == 4) {
            tables.add(R.mipmap.odonata_1)
        } else if (hydrobiontId.toInt() == 5) {
            tables.add(R.mipmap.turbellaria_1)
        } else if (hydrobiontId.toInt() == 6) {
            tables.add(R.mipmap.isopoda_1)
        } else if (hydrobiontId.toInt() == 7) {
            tables.add(R.mipmap.hirudinea_1)
        } else if (hydrobiontId.toInt() == 8 || hydrobiontId.toInt() == 9) {
            tables.add(R.mipmap.mollusca_1)
        } else if (hydrobiontId.toInt() == 10) {
            tables.add(R.mipmap.chironomidae_1)
            tables.add(R.mipmap.chironomidae_2)
        } else if (hydrobiontId.toInt() == 11) {
            tables.add(R.mipmap.oligochaeta_1)
            tables.add(R.mipmap.oligochaeta_2)
        }
    }
}