package com.example.cleanwater_project.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanwater_project.R
import com.example.cleanwater_project.presentation.main.adapters.RecyclerViewAdapter
import com.example.data.repository.Repositories
import com.example.data.research.entities.ResearchMain
import kotlinx.coroutines.*

class ResearchFragment : Fragment(R.layout.research_fragment) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val data = MutableLiveData<List<ResearchMain>>()

    private val adapter = RecyclerViewAdapter {
        findNavController().navigate(
            ResearchFragmentDirections.actionResearchFragmentToResearchNavigation(
                it.id,
                it.collectionNumber
            )
        )
    }

    override fun onPause() {
        super.onPause()
        coroutineScope.cancel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coroutineScope.launch {
            Repositories.researchRepository.getCardsResearch().collect {
                data.postValue(it)
            }
        }

        setRecyclerViewAdapter(view)

        data.observe(requireActivity()) {
            adapter.setData(it)
        }
    }

    private fun setRecyclerViewAdapter(view: View) {
        view.findViewById<RecyclerView>(R.id.research_cards).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ResearchFragment.adapter
        }
    }
}