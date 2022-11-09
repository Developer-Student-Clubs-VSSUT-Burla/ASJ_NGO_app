package com.example.asjapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.asjapp.databinding.FragmentMembersBinding
import com.example.asjapp.recyclerView.RecyclerAdapter

private var _binding: FragmentMembersBinding? = null
private val binding get() = _binding!!

class MembersFragment : Fragment() {

    lateinit var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMembersBinding.inflate(inflater, container, false)
        val view = binding.root

        adapter = RecyclerAdapter()
        binding.recyclerView.adapter = adapter

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_membersFragment_to_ngo_Profile)
        }
        return view
    }

}