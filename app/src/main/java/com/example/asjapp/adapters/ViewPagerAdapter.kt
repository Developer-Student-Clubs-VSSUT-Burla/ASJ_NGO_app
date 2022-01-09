package com.example.asjapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.asjapp.fragments.HomeFragment
import com.example.asjapp.fragments.NGOFragment
import com.example.asjapp.fragments.ProfileFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NGOFragment()
            1 -> HomeFragment()
            2 -> ProfileFragment()
            else -> HomeFragment()
        }
    }
}