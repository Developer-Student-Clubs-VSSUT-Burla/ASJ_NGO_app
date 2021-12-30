package com.pkpanda.ngofinder.adapters_Adarsh

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pkpanda.ngofinder.fragments_adarsh.HomeFragment
import com.pkpanda.ngofinder.fragments_adarsh.NGOFragment
import com.pkpanda.ngofinder.fragments_adarsh.ProfileFragment_Adarsh

class ViewPagerAdapter_Adarsh(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NGOFragment()
            1 -> HomeFragment()
            2 -> ProfileFragment_Adarsh()
            else -> HomeFragment()
        }
    }
}