package com.example.asjapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.asjapp.R
import com.example.asjapp.databinding.FragmentNgoBinding
import com.example.asjapp.recyclerView.NGOCardsAdapter

private var ngoBinding: FragmentNgoBinding? = null
private val binding get() = ngoBinding!!

class NGOFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ngoBinding = FragmentNgoBinding.inflate(inflater, container, false)
        val view = binding.root

        val names = resources.getStringArray(R.array.NGO_nameOfTheOrganisation)
        val details = resources.getStringArray(R.array.NGO_Details)
        val fullDetails = resources.getStringArray(R.array.NGO_FullDetails)

        val adapter = NGOCardsAdapter(names, details, fullDetails)
        binding.ngoCards.adapter = adapter

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        ngoBinding = null
    }
}