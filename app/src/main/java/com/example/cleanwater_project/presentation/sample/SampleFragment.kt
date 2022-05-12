package com.example.cleanwater_project.presentation.sample

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanwater_project.R
import com.example.cleanwater_project.presentation.sample.adapters.RecyclerViewAdapter
import com.example.domain.models.ResearchCard
import com.example.domain.models.SampleItem
import com.google.android.material.button.MaterialButton

class SampleFragment : Fragment(R.layout.sample_fragment) {

    private val data = listOf<SampleItem>(
        SampleItem(
            1,
            1,
            "Ручейники",
            "Количество: 0",
            "Пока ничего особенного"
        ),
        SampleItem(
            2,
            1,
            "Ручейники",
            "Количество: 0",
            "Пока ничего особенного"
        ),
        SampleItem(
            3,
            1,
            "Веснянки",
            "Количество: 0",
            "Пока ничего особенного"
        ),
        SampleItem(
            3,
            1,
            "Поденки",
            "Количество: 0",
            "Пока ничего особенного"
        ),
        SampleItem(
            3,
            1,
            "Стрекозы",
            "Количество: 0",
            "Пока ничего особенного"
        ),
        SampleItem(
            3,
            1,
            "Планарии",
            "Количество: 0",
            "Пока ничего особенного"
        ),
        SampleItem(
            3,
            1,
            "Изоподы",
            "Количество: 0",
            "Пока ничего особенного"
        ),
        SampleItem(
            3,
            1,
            "Пиявки",
            "Количество: 0",
            "Пока ничего особенного"
        ),
        SampleItem(
            3,
            1,
            "Молюски 1",
            "Количество: 0",
            "Пока ничего особенного"
        ),
        SampleItem(
            3,
            1,
            "Молюски 2",
            "Количество: 0",
            "Пока ничего особенного"
        ),
        SampleItem(
            3,
            1,
            "Хирономиды",
            "Количество: 0",
            "Пока ничего особенного"
        ),
        SampleItem(
            3,
            1,
            "Олигохеты",
            "Количество: 0",
            "Пока ничего особенного"
        ),
        SampleItem(
            3,
            1,
            "Ракообразные",
            "Количество: 0",
            "Пока ничего особенного"
        ),
        SampleItem(
            3,
            1,
            "Неопределен",
            "Количество: 0",
            "Пока ничего особенного"
        )
    )

    private val adapter = RecyclerViewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        view.findViewById<MaterialButton>(R.id.finish_button).setOnClickListener {
            findNavController().navigate(R.id.action_sampleFragment_to_checkResearchFragment)
        }

        view.findViewById<RecyclerView>(R.id.samples).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@SampleFragment.adapter
            addItemDecoration(
                DividerItemDecoration(
                    this@SampleFragment.requireContext(),
                    LinearLayoutManager.VERTICAL
                )
            )
        }
        adapter.setData(data)
    }
}