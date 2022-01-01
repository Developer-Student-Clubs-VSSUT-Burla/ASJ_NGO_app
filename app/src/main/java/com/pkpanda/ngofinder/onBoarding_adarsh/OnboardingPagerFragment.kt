package com.pkpanda.ngofinder.onBoarding_adarsh

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pkpanda.ngofinder.databinding.FragmentOnboardingPagerAdarshBinding

private var _binding: FragmentOnboardingPagerAdarshBinding? = null
private val binding get() = _binding!!

class OnboardingPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOnboardingPagerAdarshBinding.inflate(inflater, container, false)
        val view = binding.root
        val fragmentList = arrayListOf<Fragment>(
            Screen1(),
            Screen2(),
            Screen3()
        )

        val adapter = OnboardingPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle)

        binding.OnboardingPager.adapter = adapter
        binding.OnboardingPager.setPageTransformer(ZoomOutPageTransformer())

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}