package com.example.asjapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.asjapp.R
import com.example.asjapp.databinding.FragmentHomeBinding
import com.example.asjapp.recyclerView.HomeCardsAdapter

private var homeBinding: FragmentHomeBinding? = null
private val binding get() = homeBinding!!

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        val names = resources.getStringArray(R.array.Home_nameOfTheOrganisation)
        val details = resources.getStringArray(R.array.Home_Details)
        val fullDetails = resources.getStringArray(R.array.Home_FullDetails)

        val adapter = HomeCardsAdapter(names, details, fullDetails)
        binding.ngoCards.adapter = adapter

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        homeBinding = null
    }
}
