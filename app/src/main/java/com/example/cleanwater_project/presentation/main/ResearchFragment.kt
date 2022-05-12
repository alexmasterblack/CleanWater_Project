package com.example.cleanwater_project.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanwater_project.R
import com.example.cleanwater_project.presentation.main.adapters.RecyclerViewAdapter
import com.example.domain.models.ResearchCard

class ResearchFragment : Fragment(R.layout.research_fragment) {

    private val data = listOf<ResearchCard>(
        ResearchCard(
            1,
            "№ 32",
            "Приморский край",
            "с. Новицкое",
            "Ручей б/н",
            "21/03/2022   12:00"
        ),
        ResearchCard(
            2,
            "№ 32",
            "Приморский край",
            "с. Новицкое",
            "Ручей б/н",
            "21/03/2022   12:00"
        ),
        ResearchCard(
            3,
            "№ 32",
            "Приморский край",
            "с. Новицкое",
            "Ручей б/н",
            "21/03/2022   12:00"
        ),
        ResearchCard(
            4,
            "№ 32",
            "Приморский край",
            "с. Новицкое",
            "Ручей б/н",
            "21/03/2022   12:00"
        ),
        ResearchCard(
            5,
            "№ 32",
            "Приморский край",
            "с. Новицкое",
            "Ручей б/н",
            "21/03/2022   12:00"
        ),
        ResearchCard(
            6,
            "№ 32",
            "Приморский край",
            "с. Новицкое",
            "Ручей б/н",
            "21/03/2022   12:00"
        )
    )

    private val adapter = RecyclerViewAdapter {
        findNavController().navigate(R.id.action_researchFragment_to_research_navigation)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.research_cards).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ResearchFragment.adapter
        }
        adapter.setData(data)
    }
}