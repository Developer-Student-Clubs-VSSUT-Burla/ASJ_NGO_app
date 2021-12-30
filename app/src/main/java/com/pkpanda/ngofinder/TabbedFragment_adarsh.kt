package com.pkpanda.ngofinder

import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.pkpanda.ngofinder.adapters_Adarsh.ViewPagerAdapter_Adarsh
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.pkpanda.ngofinder.databinding.FragmentTabbedAdarshBinding

private var _binding: FragmentTabbedAdarshBinding? =null
private val binding get() = _binding!!

lateinit var toggle: ActionBarDrawerToggle

class TabbedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTabbedAdarshBinding.inflate(inflater,container, false)
        val view = binding.root

        val viewPagerAdapter = ViewPagerAdapter_Adarsh(requireActivity().supportFragmentManager, lifecycle)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = viewPagerAdapter
        val tabs: TabLayout = binding.tabs

        setHasOptionsMenu(true)

        binding.toolbar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.search -> {
                    // Handle search icon press
                    true
                }
                else -> false
            }
        }

        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.about ->
                    findNavController().navigate(R.id.action_tabbedFragment_to_aboutFragment)
            }
            true
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if(binding.drawerLayout.isDrawerVisible(GravityCompat.START)){
                    binding.drawerLayout.close()
                }
                else{
                    isEnabled = false
                    requireActivity().onBackPressed()
                }
            }
        })

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "NGOs"
                    tab.setIcon(R.drawable.ic_ngo_adarsh)
                }
                1 -> {
                    tab.text = "Home"
                    tab.setIcon(R.drawable.ic_home_adarsh)
                }
                2 -> {
                    tab.text = "Profile"
                    tab.setIcon(R.drawable.ic_profile_adarsh)
                }
            }
        }.attach()

        return view
    }



}