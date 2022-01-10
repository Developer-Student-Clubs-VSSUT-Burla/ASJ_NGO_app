package com.example.asjapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.asjapp.databinding.FragmentNgoProfileBinding
import com.example.asjapp.recyclerView.NGOGalleryAdapter

private var _binding: FragmentNgoProfileBinding? = null
private val binding get() = _binding!!

class NgoProfile : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        

        val galleryPics2 = intArrayOf(
            R.drawable.screen21, R.drawable.screen24, R.drawable.screen18, R.drawable.screen15
        )

        _binding = FragmentNgoProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        val adapter3 = NGOGalleryAdapter(galleryPics2)
        binding.NGOGallery.adapter = adapter3

        binding.ibJoin.setOnClickListener {
            findNavController().navigate(R.id.action_ngo_Profile_to_joinFragment)
        }

        binding.ibDonation.setOnClickListener {
            findNavController().navigate(R.id.action_ngo_Profile_to_donateFragment2)
        }

        binding.ibChat.setOnClickListener {
            findNavController().navigate(R.id.action_ngo_Profile_to_chatFragment)
        }

        binding.ibMembers.setOnClickListener {
            findNavController().navigate(R.id.action_ngo_Profile_to_membersFragment)
        }

        binding.btBack.setOnClickListener {
            findNavController().navigate(R.id.action_ngo_Profile_to_tabbedFragment)
        }
        return view
    }
}