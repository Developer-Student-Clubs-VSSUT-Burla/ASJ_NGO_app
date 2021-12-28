package com.pkpanda.ngofinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_joining_form_suraj.*

class JoiningForm_Suraj : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joining_form_suraj)

        name.apply {
            hint="Full Name"
        }
        dob.apply {
            hint="Date of Birth"
        }
        address.apply {
            hint="Address"
        }
        city.apply {
            hint="City"
        }
        state.apply {
            hint="State"
        }
        pin.apply {
            hint="Pincode"
        }
        father.apply{
            hint="Father's Name"
        }
        mother.apply {
            hint="Mother's Name"
        }

        upload.setOnClickListener(View.OnClickListener {
            Toast.makeText(this@JoiningForm_Suraj,"Application Submitted", Toast.LENGTH_SHORT).show()
        })
        photo.setOnClickListener(View.OnClickListener {
            Toast.makeText(this@JoiningForm_Suraj,"Upload ID", Toast.LENGTH_SHORT).show()
        })
        back.setOnClickListener{
            //val i= Intent(this,<PREVIOUS ACTIVITY NAME>::class.java)
            //startActivity(i)
            Toast.makeText(this@JoiningForm_Suraj,"Back Button",Toast.LENGTH_SHORT).show()
        }
    }
}