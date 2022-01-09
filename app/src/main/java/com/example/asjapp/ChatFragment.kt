package com.example.asjapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.asjapp.databinding.FragmentChatBinding
import com.example.asjapp.databinding.FragmentDonateBinding

private var _binding: FragmentChatBinding? = null
private val binding get() = _binding!!

class ChatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentChatBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_chatFragment_to_ngo_Profile)
        }

        return view
    }

}