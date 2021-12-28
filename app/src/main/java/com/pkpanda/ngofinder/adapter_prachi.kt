package com.pkpanda.ngofinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class adapter_prachi(private val vollist: ArrayList<applicants_prachi>):
    RecyclerView.Adapter<adapter_prachi.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.list_item_prachi, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem= vollist[position]
        holder.pfp.setImageResource(currentItem.pfp)
        holder.name.text=currentItem.name
        holder.mailid.text=currentItem.mailid
    }

    override fun getItemCount(): Int {
        return vollist.size
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val pfp: CircleImageView = itemView.findViewById(R.id.pic)
        val name: TextView = itemView.findViewById(R.id.name1)
        val mailid:TextView=itemView.findViewById(R.id.mailid1)
    }
}