package com.example.cleanwater_project.presentation.total.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanwater_project.R
import com.example.domain.models.SampleCheckItem

class RecyclerViewAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: MutableList<SampleCheckItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SampleItemCheckViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.sample_item_check, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SampleItemCheckViewHolder).bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(data: List<SampleCheckItem>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class SampleItemCheckViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val hydrobiontName = itemView.findViewById<TextView>(R.id.name_hydrobiont)
        private val hydrobiontLatinName =
            itemView.findViewById<TextView>(R.id.name_latin_hydrobiont)
        private val amount = itemView.findViewById<TextView>(R.id.amount)
        private val percentage = itemView.findViewById<TextView>(R.id.percent)


        fun bind(item: SampleCheckItem) {
            hydrobiontName.text = item.hydrobiontName
            hydrobiontLatinName.text = item.hydrobiontLatinName
            amount.text = item.amount.toString()
            percentage.text = item.percentage.toString()

        }
    }
}