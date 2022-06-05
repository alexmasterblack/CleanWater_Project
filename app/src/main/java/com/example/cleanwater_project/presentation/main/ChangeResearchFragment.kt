package com.example.cleanwater_project.presentation.main

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cleanwater_project.R
import com.example.data.probe.entities.Probe
import com.example.data.repository.Repositories
import com.example.data.research.entities.Research
import com.example.data.research.entities.ResearchUpdate
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ChangeResearchFragment : Fragment(R.layout.change_research_fragment) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val args by navArgs<ChangeResearchFragmentArgs>()

    private val researchId by lazy { args.researchId }

    private val data = MutableLiveData<Research>()

    private lateinit var lengthStreamTextView: AutoCompleteTextView

    private lateinit var partRiverBasinTextView: AutoCompleteTextView

    private lateinit var longitudinalZoneTextView: AutoCompleteTextView

    private lateinit var longitudinalElementRiverbed: AutoCompleteTextView

    private lateinit var transverseElementRiverbed: AutoCompleteTextView

    private lateinit var bottomSubstrateType: AutoCompleteTextView

    override fun onPause() {
        super.onPause()
        coroutineScope.cancel()
    }

    override fun onResume() {
        super.onResume()

        lengthStreamTextView.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.dropdown_menu,
                resources.getStringArray(R.array.length_stream)
            )
        )

        partRiverBasinTextView.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.dropdown_menu,
                resources.getStringArray(R.array.part_river_basin)
            )
        )

        longitudinalZoneTextView.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.dropdown_menu,
                resources.getStringArray(R.array.longitudinal_zone)
            )
        )

        longitudinalElementRiverbed.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.dropdown_menu,
                resources.getStringArray(R.array.longitudinal_element_riverbed)
            )
        )

        transverseElementRiverbed.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.dropdown_menu,
                resources.getStringArray(R.array.transverse_element_riverbed)
            )
        )

        bottomSubstrateType.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.dropdown_menu,
                resources.getStringArray(R.array.bottom_substrate_type)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        lengthStreamTextView = view.findViewById(R.id.auto_complete_length_stream)

        partRiverBasinTextView = view.findViewById(R.id.auto_complete_part_river_basin)

        longitudinalZoneTextView = view.findViewById(R.id.auto_complete_longitudinal_zone)

        longitudinalElementRiverbed =
            view.findViewById(R.id.auto_complete_longitudinal_element_riverbed)

        transverseElementRiverbed =
            view.findViewById(R.id.auto_complete_transverse_element_riverbed)

        bottomSubstrateType = view.findViewById(R.id.auto_complete_bottom_substrate_type)

        coroutineScope.launch {
            Repositories.researchRepository.getResearch(researchId).collect {
                data.postValue(it)
            }
        }

        data.observe(requireActivity()) {
            setResearch(view, it)
        }

        view.findViewById<MaterialButton>(R.id.save_research_button).setOnClickListener {
            updateResearch(view)
        }
    }

    private fun setResearch(view: View, research: Research) {

        val latitudeByHand = view.findViewById<TextInputEditText>(R.id.input_latitude_hand)
        latitudeByHand.setText(research.latitudeByHand)

        val longitudeByHand = view.findViewById<TextInputEditText>(R.id.input_longitude_hand)
        longitudeByHand.setText(research.longitudeByHand)

        val region = view.findViewById<TextInputEditText>(R.id.input_region)
        region.setText(research.region)

        val district = view.findViewById<TextInputEditText>(R.id.input_district)
        district.setText(research.district)

        val settlement = view.findViewById<TextInputEditText>(R.id.input_settlement)
        settlement.setText(research.settlement)

        val nameReservoir = view.findViewById<TextInputEditText>(R.id.input_name_reservoir)
        nameReservoir.setText(research.nameReservoir)

        val riverBasin = view.findViewById<TextInputEditText>(R.id.input_river_basin)
        riverBasin.setText(research.riverBasin)

        val lengthStream = view.findViewById<AutoCompleteTextView>(R.id.auto_complete_length_stream)
        lengthStream.setText(research.lengthStream, false)

        val airTemperature = view.findViewById<TextInputEditText>(R.id.input_air_temperature)
        airTemperature.setText(research.airTemperature.toString())

        val waterTemperature =
            view.findViewById<TextInputEditText>(R.id.input_water_temperature)
        waterTemperature.setText(research.waterTemperature.toString())

        val comment = view.findViewById<TextInputEditText>(R.id.input_comment)
        comment.setText(research.comment)

        val partRiverBasin =
            view.findViewById<AutoCompleteTextView>(R.id.auto_complete_part_river_basin)
        partRiverBasin.setText(research.partRiverBasin, false)

        val longitudinalZone =
            view.findViewById<AutoCompleteTextView>(R.id.auto_complete_longitudinal_zone)
        longitudinalZone.setText(research.longitudinalZone, false)

        val longitudinalElementRiverbed =
            view.findViewById<AutoCompleteTextView>(R.id.auto_complete_longitudinal_element_riverbed)
        longitudinalElementRiverbed.setText(research.longitudinalElementRiverbed, false)

        val transverseElementRiverbed =
            view.findViewById<AutoCompleteTextView>(R.id.auto_complete_transverse_element_riverbed)
        transverseElementRiverbed.setText(research.transverseElementRiverbed, false)

        val currentVelosity = view.findViewById<TextInputEditText>(R.id.input_current_velosity)
        currentVelosity.setText(research.currentVelosity.toString())

        val widthRiverbed = view.findViewById<TextInputEditText>(R.id.input_width_riverbed)
        widthRiverbed.setText(research.widthRiverbed.toString())

        val averageDepth = view.findViewById<TextInputEditText>(R.id.input_average_depth)
        averageDepth.setText(research.averageDepth.toString())

        val depthSamplingPoint =
            view.findViewById<TextInputEditText>(R.id.input_depth_sampling_point)
        depthSamplingPoint.setText(research.depthSamplingPoint.toString())

        val bottomSubstrateType =
            view.findViewById<AutoCompleteTextView>(R.id.auto_complete_bottom_substrate_type)
        bottomSubstrateType.setText(research.bottomSubstrateType, false)

        val developmentAquaticVegetation =
            view.findViewById<TextInputEditText>(R.id.input_development_aquatic_vegetation)
        developmentAquaticVegetation.setText(research.developmentAquaticVegetation)

        val typeRiparianVegetation =
            view.findViewById<TextInputEditText>(R.id.input_type_riparian_vegetation)
        typeRiparianVegetation.setText(research.typeRiparianVegetation)

        val illuminationRiverbed =
            view.findViewById<TextInputEditText>(R.id.input_illumination_riverbed)
        illuminationRiverbed.setText(research.illuminationRiverbed)

        val anthropogenicImpact =
            view.findViewById<TextInputEditText>(R.id.input_anthropogenic_impact)
        anthropogenicImpact.setText(research.anthropogenicImpact)

        val samplingMethod =
            view.findViewById<TextInputEditText>(R.id.input_sampling_method)
        samplingMethod.setText(research.samplingMethod)

        val typeSampler =
            view.findViewById<TextInputEditText>(R.id.input_type_sampler)
        typeSampler.setText(research.typeSampler)
    }

    private fun updateResearch(view: View) {

        var check = 0

        val latitudeByHand = view.findViewById<TextInputEditText>(R.id.input_latitude_hand)

        val longitudeByHand = view.findViewById<TextInputEditText>(R.id.input_longitude_hand)

        val region = view.findViewById<TextInputEditText>(R.id.input_region)
        if (region.text.toString().isEmpty()) {
            region.error = "Заполните поле"
        } else {
            check += 1
        }

        val district = view.findViewById<TextInputEditText>(R.id.input_district)

        val settlement = view.findViewById<TextInputEditText>(R.id.input_settlement)
        if (settlement.text.toString().isEmpty()) {
            settlement.error = "Заполните поле"
        } else {
            check += 1
        }

        val nameReservoir = view.findViewById<TextInputEditText>(R.id.input_name_reservoir)
        if (nameReservoir.text.toString().isEmpty()) {
            nameReservoir.error = "Заполните поле"
        } else {
            check += 1
        }

        val riverBasin = view.findViewById<TextInputEditText>(R.id.input_river_basin)
        if (riverBasin.text.toString().isEmpty()) {
            riverBasin.error = "Заполните поле"
        } else {
            check += 1
        }

        val lengthStream = view.findViewById<AutoCompleteTextView>(R.id.auto_complete_length_stream)

        val airTemperature = view.findViewById<TextInputEditText>(R.id.input_air_temperature)
        if (airTemperature.text.toString().isEmpty()) {
            airTemperature.error = "Заполните поле"
        } else {
            check += 1
        }

        val waterTemperature = view.findViewById<TextInputEditText>(R.id.input_water_temperature)
        if (waterTemperature.text.toString().isEmpty()) {
            waterTemperature.error = "Заполните поле"
        } else {
            check += 1
        }

        val comment = view.findViewById<TextInputEditText>(R.id.input_comment)

        val partRiverBasin =
            view.findViewById<AutoCompleteTextView>(R.id.auto_complete_part_river_basin)

        val longitudinalZone =
            view.findViewById<AutoCompleteTextView>(R.id.auto_complete_longitudinal_zone)

        val longitudinalElementRiverbed =
            view.findViewById<AutoCompleteTextView>(R.id.auto_complete_longitudinal_element_riverbed)

        val transverseElementRiverbed =
            view.findViewById<AutoCompleteTextView>(R.id.auto_complete_transverse_element_riverbed)

        val currentVelosity = view.findViewById<TextInputEditText>(R.id.input_current_velosity)
        if (currentVelosity.text.toString().isEmpty()) {
            currentVelosity.error = "Заполните поле"
        } else {
            check += 1
        }

        val widthRiverbed = view.findViewById<TextInputEditText>(R.id.input_width_riverbed)
        if (widthRiverbed.text.toString().isEmpty()) {
            widthRiverbed.error = "Заполните поле"
        } else {
            check += 1
        }

        val averageDepth = view.findViewById<TextInputEditText>(R.id.input_average_depth)
        if (averageDepth.text.toString().isEmpty()) {
            averageDepth.error = "Заполните поле"
        } else {
            check += 1
        }

        val depthSamplingPoint =
            view.findViewById<TextInputEditText>(R.id.input_depth_sampling_point)
        if (depthSamplingPoint.text.toString().isEmpty()) {
            depthSamplingPoint.error = "Заполните поле"
        } else {
            check += 1
        }

        val bottomSubstrateType =
            view.findViewById<AutoCompleteTextView>(R.id.auto_complete_bottom_substrate_type)

        val developmentAquaticVegetation =
            view.findViewById<TextInputEditText>(R.id.input_development_aquatic_vegetation)
        if (developmentAquaticVegetation.text.toString().isEmpty()) {
            developmentAquaticVegetation.error = "Заполните поле"
        } else {
            check += 1
        }

        val typeRiparianVegetation =
            view.findViewById<TextInputEditText>(R.id.input_type_riparian_vegetation)
        if (typeRiparianVegetation.text.toString().isEmpty()) {
            typeRiparianVegetation.error = "Заполните поле"
        } else {
            check += 1
        }

        val illuminationRiverbed =
            view.findViewById<TextInputEditText>(R.id.input_illumination_riverbed)
        if (illuminationRiverbed.text.toString().isEmpty()) {
            illuminationRiverbed.error = "Заполните поле"
        } else {
            check += 1
        }

        val anthropogenicImpact =
            view.findViewById<TextInputEditText>(R.id.input_anthropogenic_impact)
        if (anthropogenicImpact.text.toString().isEmpty()) {
            anthropogenicImpact.error = "Заполните поле"
        } else {
            check += 1
        }

        val samplingMethod =
            view.findViewById<TextInputEditText>(R.id.input_sampling_method)

        val typeSampler =
            view.findViewById<TextInputEditText>(R.id.input_type_sampler)

        if (check == 14) {
            val researchUpdate = ResearchUpdate(
                researchId,
                latitudeByHand?.text.toString(),
                longitudeByHand?.text.toString(),
                region.text.toString(),
                district.text.toString(),
                settlement.text.toString(),
                nameReservoir.text.toString(),
                riverBasin.text.toString(),
                lengthStream.text.toString(),
                airTemperature.text.toString().toDouble(),
                waterTemperature.text.toString()
                    .toDouble(),
                comment.text.toString(),
                partRiverBasin.text.toString(),
                longitudinalZone.text.toString(),
                longitudinalElementRiverbed.text.toString(),
                transverseElementRiverbed.text.toString(),
                currentVelosity.text.toString().toDouble(),
                widthRiverbed.text.toString().toDouble(),
                averageDepth.text.toString().toDouble(),
                depthSamplingPoint.text.toString().toDouble(),
                bottomSubstrateType.text.toString(),
                developmentAquaticVegetation.text.toString(),
                typeRiparianVegetation.text.toString(),
                illuminationRiverbed.text.toString(),
                anthropogenicImpact.text.toString(),
                samplingMethod.text.toString(),
                typeSampler.text.toString()
            )

            lifecycleScope.launch {
                Repositories.researchRepository.updateResearch(researchUpdate)
            }

            val snackbar = Snackbar.make(
                this.requireView(),
                "Исследование успешно обновлено",
                Snackbar.LENGTH_LONG
            )
            snackbar.setAction(R.string.snackbar_clear) {
                snackbar.dismiss()
            }
            snackbar.show()

            findNavController().navigateUp()
        }
    }
}