package com.example.asjapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asjapp.adapters.OwnerNgoAdapter
import com.example.asjapp.databinding.FragmentNgodBinding
import com.example.asjapp.databinding.FragmentOwnerDetailsBinding


private var _binding: FragmentNgodBinding? = null
private val binding get() = _binding!!

class ngod : Fragment() {

    lateinit var adapter: RecyclerView.Adapter<OwnerNgoAdapter.ViewHolder>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNgodBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.recyclengo.apply {
            adapter = OwnerNgoAdapter()
            binding.recyclengo.adapter = adapter
            layoutManager = LinearLayoutManager(context)
        }

        return view
    }

}