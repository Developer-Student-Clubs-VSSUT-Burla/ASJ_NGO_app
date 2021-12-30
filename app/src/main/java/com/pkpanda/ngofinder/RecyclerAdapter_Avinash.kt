package com.pkpanda.ngofinder


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter_Avinash : RecyclerView.Adapter<RecyclerAdapter_Avinash.ViewHolder>(){
    val  fname = arrayOf("Member1","Member2","Member3","Member4","Member5","Member6")
    val  desg = arrayOf("1","2","3","4","5","6")
    val pics = intArrayOf(R.drawable.ic_1,R.drawable.ic__,R.drawable.ic__,R.drawable.ic_1,R.drawable.ic__,R.drawable.ic_1)

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var image : ImageView
        var fname : TextView
        var desg : TextView
        init {
            image = itemView.findViewById(R.id.imageview)
            fname = itemView.findViewById(R.id.textview1)
            desg = itemView.findViewById(R.id.textview2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.memberlayout,parent,false)
        return ViewHolder(v)
    }



    override fun getItemCount(): Int {
        return fname.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fname.text = fname[position]
        holder.desg.text = desg[position]
        holder.image.setImageResource(pics[position])

    }
}