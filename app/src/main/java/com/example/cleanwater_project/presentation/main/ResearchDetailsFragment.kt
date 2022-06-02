package com.example.cleanwater_project.presentation.main

import android.os.Bundle
import android.view.View
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
import com.example.cleanwater_project.presentation.total.adapters.RecyclerViewAdapter
import com.example.data.probe.entities.Probe
import com.example.data.repository.Repositories
import com.example.data.research.entities.ResearchMain
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.math.RoundingMode
import java.text.DecimalFormat

class ResearchDetailsFragment : Fragment(R.layout.research_details_fragment) {

    private val args by navArgs<ResearchDetailsFragmentArgs>()

    private val researchId by lazy { args.researchId }

    private val data = MutableLiveData<List<Probe>>()

    private val main = MutableLiveData<ResearchMain?>()

    private val adapter = RecyclerViewAdapterProbe()

    private val total = MutableLiveData<Int?>()

    private val indexEPT = MutableLiveData<Double>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        GlobalScope.launch {
            Repositories.researchRepository.getMainInfo(id).collect {
                main.postValue(it)
            }
        }
    }

    private fun setTotal(view: View, total: Int?) {
        view.findViewById<TextView>(R.id.amount_total)?.text = total.toString()
    }

    private fun getTotalAmount(id: Long) {
        GlobalScope.launch {
            Repositories.probeRepository.getTotalAmount(id).collect {
                total.postValue(it)
            }
        }
    }

    private fun getProbeDataById(id: Long) {
        GlobalScope.launch {
            Repositories.probeRepository.getAllProbe(id).collect {
                data.postValue(it.filter { probe -> probe.amount != 0 })
            }
        }
    }

    private fun getIndexEPT(id: Long) {
        GlobalScope.launch {
            Repositories.probeRepository.getIndexEPT(id).collect {
                indexEPT.postValue(it)
            }
        }
    }

    private fun setIndexEPT(view: View, index: Double?) {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN

        view.findViewById<TextView>(R.id.ept_index)?.text = df.format(index).toString()
    }
}