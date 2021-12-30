package com.pkpanda.ngofinder.onBoarding_adarsh

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.pkpanda.ngofinder.R
import com.pkpanda.ngofinder.databinding.FragmentScreen1AdarshBinding

private var _binding: FragmentScreen1AdarshBinding? = null
private val binding get() = _binding!!

class Screen1 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScreen1AdarshBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewPager = activity?.findViewById<ViewPager2>(R.id.OnboardingPager)

        binding.nextButton.setOnClickListener {
            viewPager?.currentItem = 1
        }


        return view
    }

}