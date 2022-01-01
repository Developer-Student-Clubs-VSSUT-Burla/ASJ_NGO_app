package com.pkpanda.ngofinder

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_join_suraj.*

class join_suraj : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        name.apply {
            hint="Full Name"
        }
        dob.apply {
            hint="Date of Birth"
        }
        address1.apply {
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
            //
            //
        })
        photo.setOnClickListener(View.OnClickListener {
            //
            //
        })
        back.setOnClickListener{
            //
            //
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_join_suraj, container, false)



    }
}