package com.pkpanda.ngofinder.onBoarding_adarsh

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.pkpanda.ngofinder.R


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Handler(Looper.getMainLooper()).postDelayed({
            if(onBoardingFinished()){
                findNavController().navigate(R.id.action_splashFragment_to_tabbedFragment)
            }else{
                findNavController().navigate(R.id.action_splashFragment_to_onboardingPagerFragment)
            }
        }, 3000)

        return inflater.inflate(R.layout.fragment_splash_adarsh, container, false)
    }

    private fun onBoardingFinished(): Boolean{
        val sharedPref = requireActivity().getSharedPreferences("OnBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }
}