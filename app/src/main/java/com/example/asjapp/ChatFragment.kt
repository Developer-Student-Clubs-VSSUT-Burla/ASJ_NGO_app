package com.example.asjapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.asjapp.databinding.FragmentChatBinding
import com.example.asjapp.databinding.FragmentDonateBinding

private var _binding: FragmentChatBinding? = null
private val binding get() = _binding!!

class ChatFragment : Fragment() {
    private val args: ChatFragmentArgs by this.navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentChatBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.ngoContactPhn.text = args.ownerPhoneNo
        binding.ngoContactMail.text = args.ownerMailId

        binding.callbtn.setOnClickListener {
            val phoneIntent = Intent(Intent.ACTION_DIAL)
            phoneIntent.data = Uri.parse("tel:" + "${args.ownerPhoneNo}")
            startActivity(Intent.createChooser(phoneIntent, "Call Sent"))
        }

        binding.mailbtn.setOnClickListener {
            val emailIntent = Intent(
                Intent.ACTION_SENDTO,
                Uri.fromParts("mailto", "${args.ownerMailId}", null)
            )
            startActivity(Intent.createChooser(emailIntent, "Sent Mail"))
        }

        binding.back.setOnClickListener {

            findNavController().navigate(R.id.action_chatFragment_to_ngo_Profile)
        }

        return view
    }

}