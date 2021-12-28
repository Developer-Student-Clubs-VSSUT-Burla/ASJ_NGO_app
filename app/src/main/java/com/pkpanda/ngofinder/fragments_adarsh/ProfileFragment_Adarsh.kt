package com.pkpanda.ngofinder.fragments_adarsh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pkpanda.ngofinder.R

class ProfileFragment_Adarsh : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Implement Intent to navigate to Profile Activity

        return inflater.inflate(R.layout.fragment_profile_adarsh, container, false)
    }
}