package com.example.asjapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.asjapp.databinding.FragmentJoinBinding

private var _binding: FragmentJoinBinding? = null
private val binding get() = _binding!!

class JoinFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentJoinBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_joinFragment_to_ngo_Profile)
        }

        binding.name.apply {
            hint = "Full Name"
        }
        binding.dob.apply {
            hint = "Date of Birth"
        }
        binding.address.apply {
            hint = "Address"
        }
        binding.city.apply {
            hint = "City"
        }
        binding.state.apply {
            hint = "State"
        }
        binding.PinCode.apply {
            hint = "Pincode"
        }
        binding.father.apply {
            hint = "Father's Name"
        }
        binding.mother.apply {
            hint = "Mother's Name"
        }

        binding.upload.setOnClickListener(View.OnClickListener {
            Toast.makeText(context, "Application Submitted", Toast.LENGTH_SHORT).show()
        })
        binding.photo.setOnClickListener(View.OnClickListener {
            Toast.makeText(context, "Upload ID", Toast.LENGTH_SHORT).show()
        })
        return view
    }

}