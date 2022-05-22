package com.example.cleanwater_project.presentation.total

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat.getDrawable
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanwater_project.R
import com.example.cleanwater_project.presentation.total.adapters.RecyclerViewAdapter
import com.example.domain.models.SampleCheckItem
import com.google.android.material.button.MaterialButton

class CheckResearchFragment : Fragment(R.layout.check_research_fragment) {

    private val data = listOf<SampleCheckItem>(
        SampleCheckItem(1, "Ручейники", "Trichoptera", 9, 15.79),
        SampleCheckItem(1, "Веснянки", "Plecoptera", 6, 10.53),
        SampleCheckItem(1, "Поденки", "Ephemeroptera", 7, 12.28),
        SampleCheckItem(1, "Стрекозы", "Odanata", 2, 3.51)
    )

    private val adapter = RecyclerViewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        view.findViewById<MaterialButton>(R.id.continue_button).setOnClickListener {
            findNavController().navigate(R.id.action_checkResearchFragment_to_totalFragment)
        }


        view.findViewById<RecyclerView>(R.id.samples_total).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@CheckResearchFragment.adapter

            val itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            itemDecoration.setDrawable(getDrawable(this.context, R.drawable.check_divider)!!)
            addItemDecoration(itemDecoration)

            suppressLayout(true)
        }
        adapter.setData(data)
    }

}