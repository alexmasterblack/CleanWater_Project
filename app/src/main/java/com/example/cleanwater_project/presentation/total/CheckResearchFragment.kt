package com.example.cleanwater_project.presentation.total

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat.getDrawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanwater_project.R
import com.example.cleanwater_project.presentation.total.adapters.RecyclerViewAdapter
import com.example.data.probe.entities.Probe
import com.example.data.repository.Repositories
import com.example.data.research.entities.ResearchMain
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CheckResearchFragment : Fragment(R.layout.check_research_fragment) {

    private val researchId = MutableLiveData<Long>()

    private val data = MutableLiveData<List<Probe>>()

    private val main = MutableLiveData<ResearchMain?>()

    private val total = MutableLiveData<Int?>()

    private val adapter = RecyclerViewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        GlobalScope.launch {
            Repositories.researchRepository.getLastResearchId().collect {
                researchId.postValue(it)
            }
        }

        researchId.observe(requireActivity()) {
            getProbeDataById(it)
            getTotalAmount(it)
            getMainInfo(it)
            setButton(view, it)
        }

        setRecyclerViewAdapter(view)

        main.observe(requireActivity()) {
            setMainInfo(view, it)
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
}