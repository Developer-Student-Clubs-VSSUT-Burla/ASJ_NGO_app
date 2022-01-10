package com.example.asjapp.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.asjapp.R
import com.example.asjapp.TabbedFragmentDirections

class NGOCardsAdapter(private val name: Array<String>, private val detail: Array<String>, private val fullDetail: Array<String>) :
    RecyclerView.Adapter<NGOCardsAdapter.NGOViewHolder>() {

    class NGOViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val details: TextView = view.findViewById(R.id.details)
        val nameORG: TextView = view.findViewById(R.id.nameOrg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NGOViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.ngo_items_layout, parent, false)
        return NGOViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: NGOViewHolder, position: Int) {
        val currentName = name[position]
        val currentDetails = detail[position]
        val currentFullDetails = fullDetail[position]
        holder.nameORG.text = currentName
        holder.details.text = currentDetails
        holder.itemView.setOnClickListener {
            val action = TabbedFragmentDirections.actionTabbedFragmentToNgoProfile(currentName,currentDetails,currentFullDetails)
            Navigation.createNavigateOnClickListener(action).onClick(holder.itemView)
        }
    }

    override fun getItemCount(): Int {
        return detail.size
    }
}