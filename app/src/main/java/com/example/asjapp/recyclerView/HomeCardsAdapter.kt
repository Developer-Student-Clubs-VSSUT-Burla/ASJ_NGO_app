package com.example.asjapp.recyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.asjapp.TabbedFragmentDirections
import com.example.asjapp.databinding.HomeItemsLayoutBinding
import com.example.asjapp.retrofit.Ngo

class HomeCardsAdapter : RecyclerView.Adapter<HomeCardsAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: HomeItemsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Ngo>() {
        override fun areContentsTheSame(oldItem: Ngo, newItem: Ngo): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areItemsTheSame(oldItem: Ngo, newItem: Ngo): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var ngos: List<Ngo>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            HomeItemsLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.apply {
            val ngo = ngos[position]
            nameOrg.text = ngo.name
            location.text = ngo.location
            details.text = ngo.tagline
            Log.d("TAG", ngo.toString())
            holder.itemView.setOnClickListener {
                val action = TabbedFragmentDirections.actionTabbedFragmentToNgoProfile(
                    ngo.name,
                    ngo.tagline,
                    ngo.desc,
                    ngo.location,
                    ngo._id
                )
                Navigation.createNavigateOnClickListener(action).onClick(holder.itemView)
            }

        }
    }

    override fun getItemCount() = ngos.size

}
