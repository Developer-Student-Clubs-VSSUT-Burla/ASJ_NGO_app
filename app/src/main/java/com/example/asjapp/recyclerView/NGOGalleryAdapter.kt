package com.example.asjapp.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.asjapp.R

class NGOGalleryAdapter(private val galleryPics: IntArray) :
    RecyclerView.Adapter<NGOGalleryAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image3: ImageView? = itemView.findViewById(R.id.NGOGalleryPics)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.ngo_gallery_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.image3?.setImageResource(galleryPics[position])
    }

    override fun getItemCount(): Int {
        return galleryPics.size
    }
}