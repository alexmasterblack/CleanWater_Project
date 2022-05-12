package com.example.cleanwater_project.presentation.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanwater_project.R
import com.example.domain.models.ResearchCard

class RecyclerViewAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: MutableList<ResearchCard> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.research_card, parent, false)
        return ResearchCardViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ResearchCardViewHolder).bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(data: List<ResearchCard>) {
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


        fun bind(card: ResearchCard) {
            researchNumber.text = card.researchNumber
            region.text = card.region
            locality.text = card.locality
            nameReservoir.text = card.nameReservoir
            researchDate.text = card.researchDate
        }
    }
}