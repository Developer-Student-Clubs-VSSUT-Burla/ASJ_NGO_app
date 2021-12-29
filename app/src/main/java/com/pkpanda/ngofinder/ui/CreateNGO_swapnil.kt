package com.pkpanda.ngofinder.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pkpanda.ngofinder.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.create_ngo_page_swapnil.*

class CreateNGO_swapnil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        back.setOnClickListener {
//            val i = Intent(this,<PREVIOUS ACTIVITY NAME>::class.java)
//            startActivity(i)
            Toast.makeText(this@CreateNGO_swapnil,"Back Button" , Toast.LENGTH_SHORT).show()
        }
        create.setOnClickListener{
            Toast.makeText(this@CreateNGO_swapnil, "NGO Page Created", Toast.LENGTH_SHORT).show()
        }
    }
}
