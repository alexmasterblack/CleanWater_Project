package com.example.cleanwater_project.presentation.sample

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.TypedValue
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
import com.google.android.material.divider.MaterialDividerItemDecoration

class SampleFragment : Fragment(R.layout.sample_fragment) {

    private val data = listOf<SampleItem>(
        SampleItem(
            1,
            1,
            "Ручейники",
            "",
            "Пока ничего особенного",
            0
        ),
        SampleItem(
            2,
            1,
            "Ручейники",
            "",
            "Пока ничего особенного",
            0
        ),
        SampleItem(
            3,
            1,
            "Веснянки",
            "",
            "Пока ничего особенного",
            0
        ),
        SampleItem(
            3,
            1,
            "Поденки",
            "",
            "Пока ничего особенного",
            0
        ),
        SampleItem(
            3,
            1,
            "Стрекозы",
            "",
            "Пока ничего особенного",
            0
        ),
        SampleItem(
            3,
            1,
            "Планарии",
            "",
            "Пока ничего особенного",
            0
        ),
        SampleItem(
            3,
            1,
            "Изоподы",
            "",
            "Пока ничего особенного",
            0
        ),
        SampleItem(
            3,
            1,
            "Пиявки",
            "",
            "Пока ничего особенного",
            0
        ),
        SampleItem(
            3,
            1,
            "Молюски 1",
            "",
            "Пока ничего особенного",
            0
        ),
        SampleItem(
            3,
            1,
            "Молюски 2",
            "",
            "Пока ничего особенного",
            0
        ),
        SampleItem(
            3,
            1,
            "Хирономиды",
            "",
            "Пока ничего особенного",
            0
        ),
        SampleItem(
            3,
            1,
            "Олигохеты",
            "",
            "Пока ничего особенного",
            0
        ),
        SampleItem(
            3,
            1,
            "Ракообразные",
            "",
            "Пока ничего особенного",
            0
        ),
        SampleItem(
            3,
            1,
            "Неопределен",
            "",
            "Пока ничего особенного",
            0
        )
    )

    private val adapter = RecyclerViewAdapter {
        findNavController().navigate(R.id.action_sampleFragment_to_sampleDetailsFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        view.findViewById<MaterialButton>(R.id.finish_button).setOnClickListener {
            findNavController().navigate(R.id.action_sampleFragment_to_checkResearchFragment)
        }

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
        adapter.setData(data)
    }
}