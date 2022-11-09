package com.example.asjapp.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.asjapp.R

class GalleryAdapter(private val galleryPics: IntArray) :
    RecyclerView.Adapter<GalleryAdapter.ItemViewHolder>() {


    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image2: ImageView? = itemView.findViewById(R.id.GalleryPics)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.gallery_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.image2?.setImageResource(galleryPics[position])
    }

    override fun getItemCount(): Int {
        return galleryPics.size
    }
}