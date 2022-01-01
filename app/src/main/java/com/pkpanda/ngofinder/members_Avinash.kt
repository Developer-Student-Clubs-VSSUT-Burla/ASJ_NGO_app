package com.pkpanda.ngofinder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.members_avinash.*

class members_Avinash : AppCompatActivity() {
    lateinit var adapter : RecyclerView.Adapter<RecyclerAdapter_Avinash.ViewHolder>
    lateinit var layoutManager: RecyclerView.LayoutManager

    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.members_avinash)

        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        adapter = RecyclerAdapter_Avinash()
        recyclerView.adapter = adapter


    }
}
