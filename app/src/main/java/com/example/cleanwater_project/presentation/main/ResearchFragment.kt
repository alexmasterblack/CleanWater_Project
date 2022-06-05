package com.example.cleanwater_project.presentation.main

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanwater_project.R
import com.example.cleanwater_project.presentation.main.adapters.RecyclerViewAdapter
import com.example.data.repository.Repositories
import com.example.data.research.entities.ResearchMain
import kotlinx.coroutines.*

class ResearchFragment : Fragment(R.layout.research_fragment) {

    private val data = MutableLiveData<List<ResearchMain>>()

    private val adapter = RecyclerViewAdapter {
        findNavController().navigate(
            ResearchFragmentDirections.actionResearchFragmentToResearchNavigation(
                it.id,
                it.collectionNumber
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            Repositories.researchRepository.getCardsResearch().collect {
                data.postValue(it)
            }
        }

        setRecyclerViewAdapter(view)
    }

    private fun setRecyclerViewAdapter(view: View) {
        view.findViewById<RecyclerView>(R.id.research_cards).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ResearchFragment.adapter
        }

        data.observe(requireActivity()) {
            adapter.setData(it)

            if (it.isNotEmpty()) {
                view.findViewById<TextView>(R.id.empty_text_one).visibility = View.GONE
                view.findViewById<TextView>(R.id.empty_text_two).visibility = View.GONE
            }
        }
    }
}