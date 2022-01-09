package com.example.asjapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.asjapp.databinding.FragmentDonateBinding
import com.example.asjapp.databinding.FragmentJoinBinding

private var _binding: FragmentDonateBinding? = null
private val binding get() = _binding!!

class DonateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDonateBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_donateFragment_to_ngo_Profile2)
        }
        binding.submit.setOnClickListener{
            Toast.makeText(context, "Donation Successful", Toast.LENGTH_SHORT).show()
        }

        return view
    }

}