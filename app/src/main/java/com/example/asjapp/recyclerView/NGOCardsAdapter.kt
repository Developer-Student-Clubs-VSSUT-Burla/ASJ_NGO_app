package com.example.asjapp.recyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.asjapp.R
import com.example.asjapp.TabbedFragmentDirections
import com.example.asjapp.databinding.NgoItemsLayoutBinding
import com.example.asjapp.retrofit.SubscribedNgo

class NGOCardsAdapter(var ownngos: List<SubscribedNgo>) :
    RecyclerView.Adapter<NGOCardsAdapter.NGOViewHolder>() {


    var ngoName = Array<String>(ownngos[0].ngo_member!!.size) { "0" }
    var ngoloc = Array<String>(ownngos[0].ngo_member!!.size) { "0" }
    var ngotag = Array<String>(ownngos[0].ngo_member!!.size) { "0" }

    fun setData() {
        for (i in 0 until ownngos[0].ngo_member!!.size) {
            ngoName[i] = (ownngos[0].ngo_member?.get(i)!!.name)
            ngoloc[i] = (ownngos[0].ngo_member?.get(i)!!.location)
            ngotag[i] = (ownngos[0].ngo_member?.get(i)!!.tagline)
            Log.d("msg",ngoName[i].toString())

        }

    }

    inner class NGOViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tittle: TextView = itemView.findViewById(R.id.nameOrg)
        var tag: TextView = itemView.findViewById(R.id.tagline)
        var loc: TextView = itemView.findViewById(R.id.details)
    }

//    private val diffCallback = object : DiffUtil.ItemCallback<SubscribedNgo>() {
//
//        override fun areItemsTheSame(oldItem: SubscribedNgo, newItem: SubscribedNgo): Boolean {
//            return oldItem._id==newItem._id
//        }
//
//        override fun areContentsTheSame(oldItem: SubscribedNgo, newItem: SubscribedNgo): Boolean {
//            return oldItem == newItem
//        }
//
//    }

//    private val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NGOViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.ngo_items_layout, parent, false)
        return NGOViewHolder(v)
    }

    override fun onBindViewHolder(holder: NGOViewHolder, position: Int) {
        setData()
        holder.tittle.text = ngoName[position]
        holder.tag.text = ngotag[position]
        holder.loc.text = ngoloc[position]
//            holder.itemView.setOnClickListener {
//                val action =
//                    TabbedFragmentDirections.actionTabbedFragmentToNgoProfile(
//                        ngo_member.name,
//                        ngo_member.tagline,
//                        ngo_member.desc,
//                        ngo_member.location
//                    )
//                Navigation.createNavigateOnClickListener(action)
//                    .onClick(holder.itemView)
//            }

    }

    override fun getItemCount() = ngoName.size


}
