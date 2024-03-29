package com.example.asjapp.onBoarding

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.asjapp.R


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Handler(Looper.getMainLooper()).postDelayed({
            if (onBoardingFinished()) {
                if (isLoginFinished()) {
                    Log.d("HEllo","HELllo")
                    findNavController().navigate(R.id.action_splashFragment_to_tabbedFragment)
                } else if (isOwnerLoginFinished()) {
                    findNavController().navigate(R.id.action_splashFragment_to_dashboardTab)
                }
                else {
                    Log.d("HEllo","HELllo2")
                    findNavController().navigate(R.id.action_splashFragment_to_choosePage)
                }
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_onboardingPagerFragment)
            }
        }, 3000)

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    private fun onBoardingFinished(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("OnBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }

    private fun isLoginFinished(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("Login", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }

    private fun isOwnerLoginFinished(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("OwnerLogin", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }
}