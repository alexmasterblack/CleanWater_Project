package com.example.cleanwater_project.presentation.total

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getDrawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanwater_project.R
import com.example.cleanwater_project.presentation.total.adapters.RecyclerViewAdapter
import com.example.data.probe.entities.Probe
import com.example.data.repository.Repositories
import com.example.data.research.entities.ResearchMain
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.*

class CheckResearchFragment : Fragment(R.layout.check_research_fragment) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val args by navArgs<CheckResearchFragmentArgs>()

    private val researchId by lazy { args.researchId }

    private val data = MutableLiveData<List<Probe>>()

    private val main = MutableLiveData<ResearchMain?>()

    private val total = MutableLiveData<Int?>()

    private val adapter = RecyclerViewAdapter(coroutineScope)

    override fun onPause() {
        super.onPause()
        coroutineScope.cancel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.close -> setCloseAlert()
            }
            true
        }

        getProbeDataById(researchId)
        getTotalAmount(researchId)
        getMainInfo(researchId)
        setButton(view, researchId)

        setRecyclerViewAdapter(view)

        main.observe(requireActivity()) {
            setMainInfo(view, it)
        }

        data.observe(requireActivity()) {
            setupPieChart(view, it)
        }
    }

    private fun setRecyclerViewAdapter(view: View) {
        view.findViewById<RecyclerView>(R.id.samples_total).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@CheckResearchFragment.adapter

            val itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            itemDecoration.setDrawable(getDrawable(this.context, R.drawable.check_divider)!!)
            addItemDecoration(itemDecoration)
        }

        data.observe(requireActivity()) {
            adapter.setData(it)
        }

        total.observe((requireActivity())) {
            adapter.setTotal(it)
            setTotal(view, it)
        }
    }

    private fun setCloseAlert() {
        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.setTitle("Подтверждение")
        builder.setMessage("Вы уверены, что хотите отменить исследование?")
        builder.setPositiveButton("Да") { _, _ ->
            coroutineScope.launch {
                Repositories.researchRepository.deleteLastResearch()
            }
            findNavController().navigate(R.id.action_checkResearchFragment_to_inputResearchFragment)
        }
        builder.setNegativeButton("Нет") { _, _ ->

        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun setButton(view: View, id: Long) {
        view.findViewById<MaterialButton>(R.id.continue_button).setOnClickListener {
            findNavController().navigate(
                CheckResearchFragmentDirections.actionCheckResearchFragmentToTotalFragment(
                    id
                )
            )
        }
    }

    private fun setMainInfo(view: View, main: ResearchMain?) {
        view.findViewById<TextView>(R.id.date)?.text = main?.date?.substring(0, 10)
        view.findViewById<TextView>(R.id.locality)?.text = main?.settlement
        view.findViewById<TextView>(R.id.reservoir)?.text = main?.nameReservoir
        view.findViewById<TextView>(R.id.latitude)?.text = main?.latitudeByHand
        view.findViewById<TextView>(R.id.longitude)?.text = main?.longitudeByHand

        view.findViewById<Toolbar>(R.id.toolbar)?.title =
            "№ " + main?.collectionNumber.toString()
    }

    private fun getMainInfo(id: Long) {
        coroutineScope.launch {
            Repositories.researchRepository.getMainInfo(id).collect {
                main.postValue(it)
            }
        }
    }

    private fun setTotal(view: View, total: Int?) {
        view.findViewById<TextView>(R.id.amount_total)?.text = total.toString()
    }

    private fun getTotalAmount(id: Long) {
        coroutineScope.launch {
            Repositories.probeRepository.getTotalAmount(id).collect {
                total.postValue(it)
            }
        }
    }

    private fun getProbeDataById(id: Long) {
        coroutineScope.launch {
            Repositories.probeRepository.getAllProbe(id).collect {
                data.postValue(it.filter { probe -> probe.amount != 0 })
            }
        }
    }

    private fun setupPieChart(view: View, probes: List<Probe>) {
        val latinName: MutableList<String> = mutableListOf(
            "Trichoptera",
            "Plecoptera",
            "Ephemeroptera",
            "Odonata",
            "Turbellaria",
            "Isopoda",
            "Hirudinea",
            "Gastropoda",
            "Bivalvia",
            "Chironomidae",
            "Oligochaeta",
            "Crustacea",
            "Unknown"
        )

        val pieChart = view.findViewById<PieChart>(R.id.pie_сhart)
        val pieEntries = mutableListOf<PieEntry>()
        for (probe in probes) {
            pieEntries.add(
                PieEntry(
                    probe.percent.toFloat(),
                    latinName[probe.hydrobiontId.toInt() - 1]
                )
            )
        }

        val pieDataSet = PieDataSet(pieEntries, "")
        if (isAdded) {
            pieDataSet.setColors(
                ContextCompat.getColor(this.requireContext(), R.color.hydrobiont_10),
                ContextCompat.getColor(this.requireContext(), R.color.hydrobiont_2),
                ContextCompat.getColor(this.requireContext(), R.color.hydrobiont_12),
                ContextCompat.getColor(this.requireContext(), R.color.hydrobiont_3),
                ContextCompat.getColor(this.requireContext(), R.color.hydrobiont_4),
                ContextCompat.getColor(this.requireContext(), R.color.hydrobiont_9),
                ContextCompat.getColor(this.requireContext(), R.color.hydrobiont_5),
                ContextCompat.getColor(this.requireContext(), R.color.hydrobiont_6),
                ContextCompat.getColor(this.requireContext(), R.color.hydrobiont_7),
                ContextCompat.getColor(this.requireContext(), R.color.hydrobiont_1),
                ContextCompat.getColor(this.requireContext(), R.color.hydrobiont_8),
                ContextCompat.getColor(this.requireContext(), R.color.hydrobiont_11),
                ContextCompat.getColor(this.requireContext(), R.color.hydrobiont_13),
            )
        }

        val pieData = PieData(pieDataSet)
        pieData.setDrawValues(false)
        pieData.notifyDataChanged()

        pieChart.description.isEnabled = false
        pieChart.legend.isEnabled = false
        pieChart.setEntryLabelTextSize(12f)

        if (probes.isNotEmpty()) {
            view.findViewById<FrameLayout>(R.id.frame).visibility = View.GONE
            pieChart.data = pieData;
            pieChart.invalidate()
        }
    }
}