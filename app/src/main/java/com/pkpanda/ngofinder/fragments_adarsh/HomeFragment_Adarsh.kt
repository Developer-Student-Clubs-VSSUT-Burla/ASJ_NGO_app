package com.pkpanda.ngofinder.fragments_adarsh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pkpanda.ngofinder.R
import com.pkpanda.ngofinder.recyclerView_adarsh.HomeCardsAdapter_Adarsh
import com.pkpanda.ngofinder.databinding.FragmentHomeAdarshBinding

private var homeBinding: FragmentHomeAdarshBinding? = null
private val binding get() = homeBinding!!

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentHomeAdarshBinding.inflate(inflater, container, false)
        val view = binding.root

        val names = resources.getStringArray(R.array.Home_nameOfTheOrganisation)
        val details = resources.getStringArray(R.array.Home_Details)

        val adapter = HomeCardsAdapter_Adarsh(names, details)
        binding.ngoCards.adapter = adapter
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        homeBinding = null
    }
}
