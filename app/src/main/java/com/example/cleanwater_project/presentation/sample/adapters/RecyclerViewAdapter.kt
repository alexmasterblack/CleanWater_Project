package com.example.cleanwater_project.presentation.sample.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanwater_project.R
import com.example.domain.models.ResearchCard
import com.example.domain.models.SampleItem

class RecyclerViewAdapter(private val clickListener: (SampleItem) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: MutableList<SampleItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder =
            SampleItemViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.sample_item, parent, false)
            )
        viewHolder.itemView.setOnClickListener {
            clickListener(data[viewHolder.adapterPosition] as SampleItem)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SampleItemViewHolder).bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(data: List<SampleItem>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class SampleItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val animalName = itemView.findViewById<TextView>(R.id.animal_name)
        private val amount = itemView.findViewById<TextView>(R.id.amount)


        fun bind(item: SampleItem) {
            animalName.text = item.animalName
            amount.text = item.amount
        }
    }
}