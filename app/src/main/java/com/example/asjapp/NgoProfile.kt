package com.example.asjapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.asjapp.database.SessionManager
import com.example.asjapp.databinding.FragmentNgoProfileBinding
import com.example.asjapp.fragments.TAG
import com.example.asjapp.recyclerView.NGOGalleryAdapter
import com.example.asjapp.retrofit.ApiClient
import com.example.asjapp.retrofit.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

private var _binding: FragmentNgoProfileBinding? = null
private val binding get() = _binding!!

class NgoProfile : Fragment() {

    private val args: NgoProfileArgs by navArgs()
    private lateinit var sessionManager: SessionManager
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

        val name = args.nGOName
        val details = args.nGODetails
        val fullDetails = args.nGOFullDetails
        val location = args.ngoLocation

        binding.tvNgo.text = name
        binding.tvName.text = details
        binding.tvDesc.text = fullDetails
        binding.imageButton8.text = location

        binding.ibJoin.setOnClickListener {
            sessionManager = SessionManager(requireContext())
            lifecycleScope.launchWhenCreated {
                val response = ApiClient.userService.postJoinedNgo(
                    ngo = args.ngoId,
                    token = "Bearer ${sessionManager.fetchAuthToken()}"
                )
                Log.d("JoinedNgo", response.toString())
                response.enqueue(object : Callback<User?> {
                    override fun onResponse(call: Call<User?>, response: Response<User?>) {
                        if (response.isSuccessful) {
                            Log.d("JoinedNgo", "Succesfully Added")
                            findNavController().navigate(R.id.action_ngo_Profile_to_tabbedFragment)
                        } else {
                            Toast.makeText(activity, "Invalid Credentials", Toast.LENGTH_SHORT)
                                .show()
                            Log.d("test", response.body().toString())
                        }
                    }

                    override fun onFailure(call: Call<User?>, t: Throwable) {
                        Log.d("test", t.toString())
                    }

                })
            }
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