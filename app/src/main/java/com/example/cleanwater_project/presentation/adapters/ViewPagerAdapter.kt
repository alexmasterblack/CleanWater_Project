package com.example.cleanwater_project.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanwater_project.R


class ViewPagerAdapter() :
    RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>() {

    private val data: MutableList<Int> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.table_viewpager, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        holder.itemImage.setImageResource(data[position])

    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(data: List<Int>) {
        this.data.addAll(data)
    }

    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.part)
    }
}