package com.example.asjapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.asjapp.R
import com.example.asjapp.databinding.OwnerNgoItemBinding
import com.example.asjapp.ngodDirections

class OwnerNgoAdapter : RecyclerView.Adapter<OwnerNgoAdapter.ViewHolder>()
{

    private val dummyN = arrayOf("NGO1", "NGO2", "NGO3")
    private val dummyL = arrayOf("Paris", "America", "London")

    inner class ViewHolder(val binding : OwnerNgoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val Name: TextView = itemView.findViewById(R.id.Name)
        val Location : TextView = itemView.findViewById(R.id.Location)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(OwnerNgoItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun getItemCount(): Int {
        return dummyL.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            holder.Name.text = dummyN[position]
            holder.Location.text = dummyL[position]
        }

//        holder.itemView.setOnClickListener {
//            val action = ngodDirections.actionNgodToNgoDetailsOwnerFragment(dummyN[position],dummyL[position])
//            Navigation.createNavigateOnClickListener(action).onClick(holder.itemView)
//
//        }

    }

}