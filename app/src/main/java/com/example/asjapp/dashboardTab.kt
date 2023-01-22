package com.example.asjapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.asjapp.adapters.DashboardAdapter
import com.example.asjapp.databinding.FragmentDashboardTabBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


private var _binding: FragmentDashboardTabBinding? = null
private val binding get() = _binding!!

class dashboardTab : Fragment()
{
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardTabBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewPageAdapterD = DashboardAdapter(requireActivity().supportFragmentManager,lifecycle)
        val viewPagerD : ViewPager2 = binding.viewPagerD
        viewPagerD.adapter = viewPageAdapterD
        val tabsD:TabLayout = binding.tabsD

        TabLayoutMediator(tabsD,viewPagerD){ tab, position ->
            when(position)
            {
                0 -> {
                    tab.text = "NGO Detail"
                }
                1 -> {
                    tab.text = "Owner Details"
                }
            }
        }.attach()

        binding.ngobtn.setOnClickListener{
            findNavController().navigate(R.id.action_dashboardTab_to_create_Ngo)
        }

        return view

    }
}

