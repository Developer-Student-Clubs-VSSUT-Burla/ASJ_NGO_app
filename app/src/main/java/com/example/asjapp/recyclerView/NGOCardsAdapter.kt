package com.example.asjapp.recyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.asjapp.MainActivity
import com.example.asjapp.R
import com.example.asjapp.TabbedFragmentDirections
import com.example.asjapp.databinding.NgoItemsLayoutBinding
import com.example.asjapp.retrofit.SubscribedNgo
import com.example.asjapp.retrofit.SubscribedNgoX

class NGOCardsAdapter : RecyclerView.Adapter<NGOCardsAdapter.NGOViewHolder>() {

    inner class NGOViewHolder(val binding: NgoItemsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<SubscribedNgoX>() {
        override fun areItemsTheSame(oldItem: SubscribedNgoX, newItem: SubscribedNgoX): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SubscribedNgoX, newItem: SubscribedNgoX): Boolean {
            return oldItem._id == newItem._id
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)

    var subNgo: List<SubscribedNgoX>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NGOViewHolder {
        return NGOViewHolder(
            NgoItemsLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NGOViewHolder, position: Int) {
        holder.binding.apply {
            val subNgo = subNgo[position]
            nameOrg.text = subNgo.name
            holder.itemView.setOnClickListener {
                val action = TabbedFragmentDirections.actionTabbedFragmentToNgoProfile(
                    subNgo.name,
                    subNgo.desc,
                    subNgo.desc,
                    subNgo.location,
                    subNgo._id
                )
                Navigation.createNavigateOnClickListener(action).onClick(holder.itemView)
            }
        }
    }

    override fun getItemCount() = subNgo.size


}
