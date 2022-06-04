package com.example.cleanwater_project.presentation.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanwater_project.R
import com.example.cleanwater_project.presentation.total.adapters.RecyclerViewAdapter
import com.example.data.probe.entities.Probe
import com.example.data.repository.Repositories
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.math.RoundingMode
import java.text.DecimalFormat

class RecyclerViewAdapterProbe :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: MutableList<Probe> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProbeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.sample_item_check, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RecyclerViewAdapterProbe.ProbeViewHolder).bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(data: List<Probe>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ProbeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val hydrobiontName = itemView.findViewById<TextView>(R.id.name_hydrobiont)
        private val hydrobiontLatinName =
            itemView.findViewById<TextView>(R.id.name_latin_hydrobiont)
        private val amount = itemView.findViewById<TextView>(R.id.amount)
        private val percentage = itemView.findViewById<TextView>(R.id.percent)


        fun bind(item: Probe) {
            val name: MutableList<String> = mutableListOf(
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

            hydrobiontName.text = name[item.hydrobiontId.toInt() - 1]
            hydrobiontLatinName.text = latinName[item.hydrobiontId.toInt() - 1]
            amount.text = item.amount.toString()

            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.DOWN

            percentage.text = df.format(item.percent).toString()

        }
    }
}