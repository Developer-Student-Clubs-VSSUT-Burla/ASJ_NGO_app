package com.example.asjapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.asjapp.databinding.FragmentChoosePageBinding


private lateinit var choosePageBinding: FragmentChoosePageBinding
private val binding get() = choosePageBinding!!

class ChoosePage : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        choosePageBinding=FragmentChoosePageBinding.inflate(inflater,container,false)
        val view = binding.root

        binding.linearLayout2.setOnClickListener {
            findNavController().navigate(R.id.action_choosePage_to_ownerDetails)
        }
        binding.linearLayout3.setOnClickListener {
            findNavController().navigate(R.id.action_choosePage_to_registerFragment)
        }

        return view
    }


}