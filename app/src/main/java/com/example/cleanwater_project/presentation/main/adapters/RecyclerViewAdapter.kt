package com.example.cleanwater_project.presentation.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanwater_project.R
import com.example.data.research.entities.ResearchMain

class RecyclerViewAdapter(private val clickListener: (ResearchMain) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: MutableList<ResearchMain> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder =
            ResearchCardViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.research_card, parent, false)
            )
        viewHolder.itemView.setOnClickListener {
            clickListener(data[viewHolder.adapterPosition] as ResearchMain)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ResearchCardViewHolder).bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(data: List<ResearchMain>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ResearchCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val researchNumber = itemView.findViewById<TextView>(R.id.research_number)
        private val region = itemView.findViewById<TextView>(R.id.region)
        private val locality = itemView.findViewById<TextView>(R.id.locality)
        private val nameReservoir = itemView.findViewById<TextView>(R.id.name_reservoir)
        private val researchDate = itemView.findViewById<TextView>(R.id.research_date)


        fun bind(card: ResearchMain) {
            researchNumber.text = "№ " + card.collectionNumber.toString()
            region.text = card.region
            locality.text = card.settlement
            nameReservoir.text = card.nameReservoir
            researchDate.text = card.date
        }
    }
}