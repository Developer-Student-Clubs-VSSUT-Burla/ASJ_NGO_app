package com.example.asjapp.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.asjapp.R
import com.example.asjapp.TabbedFragment
import com.example.asjapp.TabbedFragmentDirections
import com.example.asjapp.databinding.HomeItemsLayoutBinding
import com.example.asjapp.retrofit.Ngo

class HomeCardsAdapter:RecyclerView.Adapter<HomeCardsAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding:HomeItemsLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback= object : DiffUtil.ItemCallback<Ngo>(){
        override fun areContentsTheSame(oldItem: Ngo, newItem: Ngo): Boolean {
            return oldItem._id==newItem._id
        }

        override fun areItemsTheSame(oldItem: Ngo, newItem: Ngo): Boolean {
            return oldItem==newItem
        }
    }

    private val differ = AsyncListDiffer(this,diffCallback)

    var ngos: List<Ngo>
        get() = differ.currentList
        set(value) {differ.submitList(value)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(HomeItemsLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.apply {
            val ngo=ngos[position]
            nameOrg.text=ngo.name
            location.text=ngo.location
            details.text=ngo.tagline


        }
    }

    override fun getItemCount()=ngos.size

}
