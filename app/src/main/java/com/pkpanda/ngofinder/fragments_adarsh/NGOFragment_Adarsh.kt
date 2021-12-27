package com.pkpanda.ngofinder.fragments_adarsh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pkpanda.ngofinder.R
import com.pkpanda.ngofinder.recyclerView_adarsh.NGOCardsAdapter_Adarsh
import com.pkpanda.ngofinder.databinding.FragmentNgoAdarshBinding

private var ngoBinding: FragmentNgoAdarshBinding? = null
private val binding get() = ngoBinding!!

class NGOFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ngoBinding = FragmentNgoAdarshBinding.inflate(inflater, container, false)
        val view = binding.root

        val names = resources.getStringArray(R.array.NGO_nameOfTheOrganisation)
        val details = resources.getStringArray(R.array.NGO_Details)

        val adapter = NGOCardsAdapter_Adarsh(names, details)
        binding.ngoCards.adapter = adapter

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        ngoBinding = null
    }
}