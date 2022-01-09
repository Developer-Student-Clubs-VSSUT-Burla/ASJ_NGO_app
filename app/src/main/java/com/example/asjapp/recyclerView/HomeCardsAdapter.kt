package com.example.asjapp.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.asjapp.R

class HomeCardsAdapter(
    private val name: Array<String>,
    private val detail: Array<String>
) :
    RecyclerView.Adapter<HomeCardsAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val details: TextView = view.findViewById(R.id.details)
        val nameORG: TextView = view.findViewById(R.id.nameOrg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_items_layout, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentName = name[position]
        val currentDetails = detail[position]
        holder.nameORG.text = currentName
        holder.details.text = currentDetails
        holder.itemView.setOnClickListener {
            Navigation.createNavigateOnClickListener(R.id.action_tabbedFragment_to_ngo_Profile)
                .onClick(holder.itemView)
        }
    }

    override fun getItemCount(): Int {
        return detail.size
    }
}
