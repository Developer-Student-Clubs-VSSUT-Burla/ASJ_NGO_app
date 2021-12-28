package com.pkpanda.ngofinder.recyclerView_adarsh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pkpanda.ngofinder.R

class NGOCardsAdapter_Adarsh(private val name: Array<String>, private val detail: Array<String>) :
    RecyclerView.Adapter<NGOCardsAdapter_Adarsh.NGOViewHolder>() {

    class NGOViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val details: TextView = view.findViewById(R.id.details)
        val nameORG: TextView = view.findViewById(R.id.nameOrg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NGOViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.ngo_items_layout_adarsh, parent, false)
        return NGOViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: NGOViewHolder, position: Int) {
        val currentName = name[position]
        val currentDetails = detail[position]
        holder.nameORG.text = currentName
        holder.details.text = currentDetails
    }

    override fun getItemCount(): Int {
        return detail.size
    }
}