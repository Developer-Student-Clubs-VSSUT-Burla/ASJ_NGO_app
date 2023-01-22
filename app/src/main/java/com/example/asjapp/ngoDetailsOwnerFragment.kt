package com.example.asjapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.asjapp.databinding.FragmentNgoDetailsOwnerBinding


private var _binding: FragmentNgoDetailsOwnerBinding? = null
private val binding get() = _binding!!

class ngoDetailsOwnerFragment : Fragment() {

//    private val args: ngoDetailsOwnerFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNgoDetailsOwnerBinding.inflate(inflater, container, false)
        val view = binding.root

//        binding.ngoname.text = args.ngoName
//        binding.ngolocation.text = args.ngoLocation

        return view

    }

}