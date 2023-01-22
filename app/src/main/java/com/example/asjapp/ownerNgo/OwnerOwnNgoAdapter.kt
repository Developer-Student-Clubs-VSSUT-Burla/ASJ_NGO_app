package com.example.asjapp.ownerNgo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.asjapp.databinding.OwnerNgoItemBinding
import com.example.asjapp.retrofit.Ngo

class OwnerOwnNgoAdapter : RecyclerView.Adapter<OwnerOwnNgoAdapter.OwnerNgoViewHolder>() {

    inner class OwnerNgoViewHolder(val binding: OwnerNgoItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Ngo>() {
        override fun areItemsTheSame(oldItem: Ngo, newItem: Ngo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Ngo, newItem: Ngo): Boolean {
            return oldItem._id == newItem._id
        }


    }
    private val differ = AsyncListDiffer(this, diffCallback)

    var ownNgo: List<Ngo>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OwnerNgoViewHolder {
        return OwnerNgoViewHolder(
            OwnerNgoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OwnerNgoViewHolder, position: Int) {
        holder.binding.apply {
            val ownNgo = ownNgo[position]
            Name.text = ownNgo.name
            Location.text = ownNgo.location

        }
    }

    override fun getItemCount() = ownNgo.size
}