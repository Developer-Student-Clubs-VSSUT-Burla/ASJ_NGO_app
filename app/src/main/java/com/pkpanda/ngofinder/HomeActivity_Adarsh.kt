package com.pkpanda.ngofinder

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.pkpanda.ngofinder.adapters_Adarsh.ViewPagerAdapter_Adarsh
import com.pkpanda.ngofinder.databinding.ActivityHomeAdarshBinding

class HomeActivity_Adarsh : AppCompatActivity() {

    private lateinit var binding: ActivityHomeAdarshBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeAdarshBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPagerAdapter = ViewPagerAdapter_Adarsh(supportFragmentManager, lifecycle)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = viewPagerAdapter
        val tabs: TabLayout = binding.tabs

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
    }
}