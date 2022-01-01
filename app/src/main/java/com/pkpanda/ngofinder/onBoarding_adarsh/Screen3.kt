package com.pkpanda.ngofinder.onBoarding_adarsh

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.pkpanda.ngofinder.R
import com.pkpanda.ngofinder.databinding.FragmentScreen3AdarshBinding

private var _binding: FragmentScreen3AdarshBinding? = null
private val binding get() = _binding!!

class Screen3 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentScreen3AdarshBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.GetStart.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingPagerFragment_to_tabbedFragment)
            onBoardingFinished()
        }

        return view
    }

    private fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("OnBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

}