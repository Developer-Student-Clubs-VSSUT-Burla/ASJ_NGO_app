package com.example.asjapp.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.asjapp.TabbedFragmentDirections
import com.example.asjapp.databinding.NgoItemsLayoutBinding
import com.example.asjapp.retrofit.SubscribedNgo

class NGOCardsAdapter() : RecyclerView.Adapter<NGOCardsAdapter.NGOViewHolder>() {

    inner class NGOViewHolder(val binding: NgoItemsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<SubscribedNgo>() {

        override fun areItemsTheSame(oldItem: SubscribedNgo, newItem: SubscribedNgo): Boolean {
            return oldItem._id==newItem._id
        }

        override fun areContentsTheSame(oldItem: SubscribedNgo, newItem: SubscribedNgo): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var ownngos: List<SubscribedNgo>
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
            val ngo = ownngos[position].ngo_member
            val ngo_member=ngo[position]
            nameOrg.text = ngo_member.name
            tagline.text = ngo_member.tagline
            details.text = ngo_member.desc
            holder.itemView.setOnClickListener {
                val action =
                    TabbedFragmentDirections.actionTabbedFragmentToNgoProfile(
                        ngo_member.name,
                        ngo_member.tagline,
                        ngo_member.desc,
                        ngo_member.location
                    )
                Navigation.createNavigateOnClickListener(action)
                    .onClick(holder.itemView)
            }
        }
    }

    override fun getItemCount() = ownngos.size
}
