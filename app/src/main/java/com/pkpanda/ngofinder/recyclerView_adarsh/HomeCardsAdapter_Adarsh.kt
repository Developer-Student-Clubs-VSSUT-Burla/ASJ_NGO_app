package com.pkpanda.ngofinder.recyclerView_adarsh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pkpanda.ngofinder.R

class HomeCardsAdapter_Adarsh(private val name: Array<String>, private val detail: Array<String>) :
    RecyclerView.Adapter<HomeCardsAdapter_Adarsh.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val details: TextView = view.findViewById(R.id.details)
        val nameORG: TextView = view.findViewById(R.id.nameOrg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.home_items_layout_adarsh, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentName = name[position]
        val currentDetails = detail[position]
        holder.nameORG.text = currentName
        holder.details.text = currentDetails
    }

    override fun getItemCount(): Int {
        return detail.size
    }
}