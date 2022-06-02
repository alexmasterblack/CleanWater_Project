package com.example.cleanwater_project.presentation.sample.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanwater_project.R
import com.example.data.probe.entities.Probe
import com.example.domain.models.ResearchCard
import com.example.domain.models.SampleItem

class RecyclerViewAdapter(private val clickListener: (Probe) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: MutableList<Probe> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder =
            SampleItemViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.sample_item, parent, false)
            )
        viewHolder.itemView.setOnClickListener {
            clickListener(data[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SampleItemViewHolder).bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(data: List<Probe>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class SampleItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val hydrobiontName = itemView.findViewById<TextView>(R.id.animal_name)
        private val amount = itemView.findViewById<TextView>(R.id.amount)


        fun bind(item: Probe) {
            val dict: MutableList<String> = mutableListOf(
                "Ручейники",
                "Веснянки",
                "Поденки",
                "Стрекозы",
                "Планарии",
                "Изоподы",
                "Пиявки",
                "Моллюски",
                "Моллюски",
                "Хирономиды",
                "Олигохеты",
                "Ракообразные",
                "Неопределен"
            )

            val latinName: MutableList<String> = mutableListOf(
                "Trichoptera",
                "Plecoptera",
                "Ephemeroptera",
                "Odonata",
                "Turbellaria",
                "Isopoda",
                "Hirudinea",
                "Gastropoda",
                "Bivalvia",
                "Chironomidae",
                "Oligochaeta",
                "Crustacea",
                "Unknown"
            )

            hydrobiontName.text = dict[item.hydrobiontId.toInt() - 1] + " (" + latinName[item.hydrobiontId.toInt() - 1] + ")"
            amount.text = "Количество: " + item.amount.toString()
        }
    }
}