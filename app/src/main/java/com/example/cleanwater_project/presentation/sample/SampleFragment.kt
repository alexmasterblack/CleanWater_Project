package com.example.cleanwater_project.presentation.sample

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanwater_project.R
import com.example.cleanwater_project.presentation.sample.adapters.RecyclerViewAdapter
import com.example.data.hydrobionts.entities.Hydrobiont
import com.example.data.probe.entities.Probe
import com.example.data.repository.Repositories
import com.example.data.research.entities.ResearchMain
import com.example.domain.models.ResearchCard
import com.example.domain.models.SampleItem
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SampleFragment : Fragment(R.layout.sample_fragment) {

    private val researchId = MutableLiveData<Long>()

    private val data = MutableLiveData<List<Probe>>()

    private val hydrobionts = MutableLiveData<List<Hydrobiont>>()

    private val adapter = RecyclerViewAdapter {
        val ids =
            SampleFragmentDirections.actionSampleFragmentToSampleDetailsFragment(
                it.researchId,
                it.hydrobiontId,
                it.amount
            )
        findNavController().navigate(ids)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            setAlert()
        }

        GlobalScope.launch {
            Repositories.researchRepository.getLastResearchId().collect {
                researchId.postValue(it)
            }
            Repositories.hydrobiontRepository.getHydrobionts().collect {
                hydrobionts.postValue(it)
            }
        }

        researchId.observe(requireActivity()) {
            setInitialProbes(it)
            getProbeDataById(it)
        }

        setRecyclerViewAdapter(view)

        view.findViewById<MaterialButton>(R.id.finish_button).setOnClickListener {
            findNavController().navigate(R.id.action_sampleFragment_to_checkResearchFragment)
        }
    }

    private fun setAlert() {
        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.setTitle("Отмена")
        builder.setMessage("Вы уверены, что хотите отменить исследование?")
        builder.setPositiveButton("Да") { _, _ ->
            GlobalScope.launch {
                Repositories.researchRepository.deleteLastResearch()
            }
            findNavController().navigateUp()
        }
        builder.setNegativeButton("Нет") { _, _ ->

        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun setRecyclerViewAdapter(view: View) {
        val divider =
            MaterialDividerItemDecoration(this.requireContext(), LinearLayoutManager.VERTICAL)

        view.findViewById<RecyclerView>(R.id.samples).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@SampleFragment.adapter
            divider.dividerInsetStart = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                90f,
                resources.displayMetrics
            ).toInt()
            divider.dividerInsetEnd = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                20f,
                resources.displayMetrics
            ).toInt()
            addItemDecoration(divider)
        }

        data.observe(requireActivity()) {
            adapter.setData(it)
        }
    }

    private fun getProbeDataById(id: Long) {
        GlobalScope.launch {
            Repositories.probeRepository.getAllProbe(id).collect {
                data.postValue(it)
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

        GlobalScope.launch {
            for (probe in probes) {
                Repositories.probeRepository.addProbe(probe)
            }
        }
    }
}