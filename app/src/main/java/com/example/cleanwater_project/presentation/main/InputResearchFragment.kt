package com.example.cleanwater_project.presentation.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.cleanwater_project.R
import com.example.data.probe.entities.Probe
import com.example.data.repository.Repositories
import com.example.data.research.entities.Research
import com.google.android.gms.location.*
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.*
import org.joda.time.*


class InputResearchFragment : Fragment(R.layout.input_research_fragment) {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var locationRequest: LocationRequest

    private lateinit var locationCallback: LocationCallback

    private val researchId = MutableLiveData<Long>()

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    override fun onResume() {
        super.onResume()
        startLocationUpdates()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(this.requireActivity())

        getLocationUpdates(view)

        view.findViewById<AutoCompleteTextView>(R.id.auto_complete_length_stream)
            .setAdapter(
                ArrayAdapter(
                    requireContext(),
                    R.layout.dropdown_menu,
                    resources.getStringArray(R.array.length_stream)
                )
            )

        view.findViewById<AutoCompleteTextView>(R.id.auto_complete_part_river_basin)
            .setAdapter(
                ArrayAdapter(
                    requireContext(),
                    R.layout.dropdown_menu,
                    resources.getStringArray(R.array.part_river_basin)
                )
            )

        view.findViewById<AutoCompleteTextView>(R.id.auto_complete_longitudinal_zone)
            .setAdapter(
                ArrayAdapter(
                    requireContext(),
                    R.layout.dropdown_menu,
                    resources.getStringArray(R.array.longitudinal_zone)
                )
            )

        view.findViewById<AutoCompleteTextView>(R.id.auto_complete_longitudinal_element_riverbed)
            .setAdapter(
                ArrayAdapter(
                    requireContext(),
                    R.layout.dropdown_menu,
                    resources.getStringArray(R.array.longitudinal_element_riverbed)
                )
            )

        view.findViewById<AutoCompleteTextView>(R.id.auto_complete_transverse_element_riverbed)
            .setAdapter(
                ArrayAdapter(
                    requireContext(),
                    R.layout.dropdown_menu,
                    resources.getStringArray(R.array.transverse_element_riverbed)
                )
            )

        view.findViewById<AutoCompleteTextView>(R.id.auto_complete_bottom_substrate_type)
            .setAdapter(
                ArrayAdapter(
                    requireContext(),
                    R.layout.dropdown_menu,
                    resources.getStringArray(R.array.bottom_substrate_type)
                )
            )

        view.findViewById<TextInputEditText>(R.id.input_sampling_date)
            .setText(LocalDate.now().toString("dd.MM.YYYY"))
        view.findViewById<TextInputEditText>(R.id.input_sampling_time)
            .setText(LocalTime.now().toString("hh:mm"))

        view.findViewById<MaterialButton>(R.id.add_research_button).setOnClickListener {

            var check = 0

            val collectionNumber =
                view.findViewById<TextInputEditText>(R.id.input_collection_number)
            if (collectionNumber.text.toString().isEmpty()) {
                collectionNumber.error = "Заполните поле"
//                collectionNumber.setBackgroundResource(R.drawable.error_status)
            } else {
//                collectionNumber.setBackgroundResource(R.drawable.normal_status)
                check += 1
            }

            val date =
                view.findViewById<TextInputEditText>(R.id.input_sampling_date)
            if (date.text.toString().isEmpty()) {
                date.error = "Заполните поле"
            } else {
                check += 1
            }

            val time = view.findViewById<TextInputEditText>(R.id.input_sampling_time)
            if (time.text.toString().isEmpty()) {
                time.error = "Заполните поле"
            } else {
                check += 1
            }

            val latitudeByHand = view.findViewById<TextInputEditText>(R.id.input_latitude_hand)
//            if (latitudeByHand.text.toString().isEmpty()) {
//                latitudeByHand.error = "Заполните поле"
//            } else {
//                check += 1
//            }

            // может быть и не заполнено
            val latitudeAuto = view.findViewById<TextInputEditText>(R.id.input_latitude_auto)

            val longitudeByHand = view.findViewById<TextInputEditText>(R.id.input_longitude_hand)
//            if (longitudeByHand.text.toString().isEmpty()) {
//                longitudeByHand.error = "Заполните поле"
//            } else {
//                check += 1
//            }

            val longitudeAuto = view.findViewById<TextInputEditText>(R.id.input_longitude_auto)

            val region = view.findViewById<TextInputEditText>(R.id.input_region)
//            if (region.text.toString().isEmpty()) {
//                region.error = "Заполните поле"
//            } else {
//                check += 1
//            }

            val district = view.findViewById<TextInputEditText>(R.id.input_district)
            if (district.text.toString().isEmpty()) {
                district.error = "Заполните поле"
            } else {
                check += 1
            }

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

            // всегда будет заполнено
            val lengthStream =
                view.findViewById<AutoCompleteTextView>(R.id.auto_complete_length_stream)

            val airTemperature = view.findViewById<TextInputEditText>(R.id.input_air_temperature)
            if (airTemperature.text.toString().isEmpty()) {
                airTemperature.error = "Заполните поле"
            } else {
                check += 1
            }

            val waterTemperature =
                view.findViewById<TextInputEditText>(R.id.input_water_temperature)
            if (waterTemperature.text.toString().isEmpty()) {
                waterTemperature.error = "Заполните поле"
            } else {
                check += 1
            }

            // не обязательно
            val comment = view.findViewById<TextInputEditText>(R.id.input_comment)

            // всегда будет заполнено
            val partRiverBasin =
                view.findViewById<AutoCompleteTextView>(R.id.auto_complete_part_river_basin)

            // всегда будет заполнено
            val longitudinalZone =
                view.findViewById<AutoCompleteTextView>(R.id.auto_complete_longitudinal_zone)

            // всегда будет заполнено
            val longitudinalElementRiverbed =
                view.findViewById<AutoCompleteTextView>(R.id.auto_complete_longitudinal_element_riverbed)

            // всегда будет заполнено
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

            // всегда будет заполнено
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

            // заполнено
            val samplingMethod =
                view.findViewById<TextInputEditText>(R.id.input_sampling_method)

            // заполнено
            val typeSampler =
                view.findViewById<TextInputEditText>(R.id.input_type_sampler)

            if (check == 17) {
                val research = Research(
                    0,
                    collectionNumber.text.toString().toLong(),
                    date.text.toString() + " " + time.text.toString(),
                    latitudeByHand?.text.toString(),
                    latitudeAuto?.text.toString(),
                    longitudeByHand?.text.toString(),
                    longitudeAuto?.text.toString(),
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
                    Repositories.researchRepository.addResearch(research)
                }

                lifecycleScope.launch {
                    Repositories.researchRepository.getLastResearchId().collect {
                        researchId.postValue(it)
                    }
                }

                researchId.observe(requireActivity()) {
                    setInitialProbes(it)
                }

                findNavController().navigate(R.id.action_inputResearchFragment_to_sampleFragment)
            }
        }
    }

    private fun setInitialProbes(id: Long) {
        val probes = listOf<Probe>(
            Probe(
                id,
                1,
                0,
                0.0
            ),
            Probe(
                id,
                2,
                0,
                0.0
            ),
            Probe(
                id,
                3,
                0,
                0.0
            ),
            Probe(
                id,
                4,
                0,
                0.0
            ),
            Probe(
                id,
                5,
                0,
                0.0
            ),
            Probe(
                id,
                6,
                0,
                0.0
            ),
            Probe(
                id,
                7,
                0,
                0.0
            ),
            Probe(
                id,
                8,
                0,
                0.0
            ),
            Probe(
                id,
                9,
                0,
                0.0
            ),
            Probe(
                id,
                10,
                0,
                0.0
            ),
            Probe(
                id,
                11,
                0,
                0.0
            ),
            Probe(
                id,
                12,
                0,
                0.0
            ),
            Probe(
                id,
                13,
                0,
                0.0
            ),
        )

        lifecycleScope.launch {
            for (probe in probes) {
                Repositories.probeRepository.addProbe(probe)
            }
        }
    }

    private fun getLocationUpdates(view: View) {
        fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(this.requireActivity())

        locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setFastestInterval(50000)
            .setSmallestDisplacement(170f)
            .setInterval(50000)

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                if (p0.locations.isNotEmpty()) {
                    val location = p0.lastLocation

                    Log.d(
                        "Location",
                        "Latitude: ${location.latitude}, Longitude: ${location.longitude}"
                    )

                    view.findViewById<TextInputEditText>(R.id.input_longitude_auto)
                        .setText(location.longitude.toString())
                    view.findViewById<TextInputEditText>(R.id.input_latitude_auto)
                        .setText(location.latitude.toString())
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

}