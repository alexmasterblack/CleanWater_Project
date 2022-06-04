package com.example.cleanwater_project.presentation.main

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanwater_project.R
import com.example.cleanwater_project.presentation.main.adapters.RecyclerViewAdapterProbe
import com.example.data.index.entities.IndexValue
import com.example.data.probe.entities.Probe
import com.example.data.repository.Repositories
import com.example.data.research.entities.ResearchMain
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlinx.coroutines.*

class ResearchDetailsFragment : Fragment(R.layout.research_details_fragment) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val args by navArgs<ResearchDetailsFragmentArgs>()

    private val researchId by lazy { args.researchId }

    private val collectionNumber by lazy { args.collectionNumber }

    private val data = MutableLiveData<List<Probe>>()

    private val main = MutableLiveData<ResearchMain?>()

    private val adapter = RecyclerViewAdapterProbe()

    private val total = MutableLiveData<Int?>()

    private val indexEPT = MutableLiveData<IndexValue>()

    override fun onPause() {
        super.onPause()
        coroutineScope.cancel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Toolbar>(R.id.toolbar).title =
            "Исследование № $collectionNumber"

        view.findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        getProbeDataById(researchId)
        getTotalAmount(researchId)

        total.observe((requireActivity())) {
            setTotal(view, it)
        }

        getMainInfo(researchId)
        main.observe(requireActivity()) {
            setMainInfo(view, it)
        }

        getIndexEPT(researchId)
        indexEPT.observe(requireActivity()) {
            setIndexEPT(view, it)
        }

        setRecyclerViewAdapter(view)

        data.observe(requireActivity()) {
            setupPieChart(view, it)
        }
    }

    private fun setRecyclerViewAdapter(view: View) {
        view.findViewById<RecyclerView>(R.id.samples_total).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ResearchDetailsFragment.adapter

            val itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            itemDecoration.setDrawable(
                ContextCompat.getDrawable(
                    this.context,
                    R.drawable.check_divider
                )!!
            )
            addItemDecoration(itemDecoration)
        }

        data.observe(requireActivity()) {
            adapter.setData(it)
        }
    }

    private fun setMainInfo(view: View, main: ResearchMain?) {
        view.findViewById<TextView>(R.id.date)?.text = main?.date?.substring(0, 10)
        view.findViewById<TextView>(R.id.locality)?.text = main?.settlement
        view.findViewById<TextView>(R.id.reservoir)?.text = main?.nameReservoir
        view.findViewById<TextView>(R.id.latitude)?.text = main?.latitudeByHand
        view.findViewById<TextView>(R.id.longitude)?.text = main?.longitudeByHand
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

    private fun getIndexEPT(id: Long) {
        coroutineScope.launch {
            Repositories.indexValueRepository.getEPTIndex(id, 1).collect {
                indexEPT.postValue(it)
            }
        }
    }

    private fun setIndexEPT(view: View, index: IndexValue) {
        val indexEPT = view.findViewById<TextView>(R.id.ept_index)

        if (index.waterQuality == "Очень хорошее") {
            indexEPT?.setTextColor(resources.getColor(R.color.very_good_quality))
        } else if (index.waterQuality == "Хорошее") {
            indexEPT?.setTextColor(resources.getColor(R.color.good_quality))
        } else if (index.waterQuality == "Посредственное") {
            indexEPT?.setTextColor(resources.getColor(R.color.not_bad_quality))
        } else if (index.waterQuality == "Плохое") {
            indexEPT?.setTextColor(resources.getColor(R.color.bad_quality))
        } else if (index.waterQuality == "Очень плохое") {
            indexEPT?.setTextColor(resources.getColor(R.color.very_bad_quality))
        }

        indexEPT?.text = index.waterQuality
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

        pieChart.animateXY(1000, 1000)

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