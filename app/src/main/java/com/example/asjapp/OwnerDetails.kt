package com.example.asjapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.asjapp.database.UserDatabase
import com.example.asjapp.database.UserEntity
import com.example.asjapp.databinding.FragmentOwnerDetailsBinding
import com.example.asjapp.retrofit.ApiClient
import com.example.asjapp.retrofit.RequestOwner
import com.example.asjapp.retrofit.ResponseNgo
import com.example.asjapp.retrofit.ResponseOwner
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private var _binding: FragmentOwnerDetailsBinding? = null
private val binding get() = _binding!!

class OwnerDetails : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOwnerDetailsBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.moveOwnerLogin.setOnClickListener {
            findNavController().navigate(R.id.action_ownerDetails_to_ownerLoginFragment)
        }

        binding.materialButton.setOnClickListener {
            if (binding.ownerName.text.toString().isNotEmpty() &&
                binding.ownerEmail.text.toString().isNotEmpty() &&
                binding.ownerPassword.text.toString().isNotEmpty() &&
                binding.ownerAddress2.text.toString().isNotEmpty()
            ) {
                val name = binding.ownerName.text.toString()
                val email = binding.ownerEmail.text.toString()
                val passsword = binding.ownerPassword.text.toString()
                val address = binding.ownerAddress2.text.toString()

                val owner = RequestOwner(name = name, email = email, password = passsword,address=address)
                val ownerReponseCall = ApiClient.userService.registerNgoOwner(owner)

                ownerReponseCall.enqueue(object : Callback<ResponseOwner> {
                    override fun onResponse(
                        call: Call<ResponseOwner>,
                        response: Response<ResponseOwner>
                    ) {
                        if (response.isSuccessful) {
                            GlobalScope.launch {
                                context?.let {
                                    val userDetails = UserEntity(
                                        0,
                                        response.body()?.name.toString(),
                                        response.body()?.email.toString(),
                                        "Enter bio".toString(),
                                        response.body()?.token.toString(),
                                        response.body()?._id!!.toString()
                                    )
                                    UserDatabase(it).getUserDao().addUser(userDetails)
                                    isOwnerLoginFinished()
                                }
                            }
                            Log.d("test owner", response.body().toString())
                            Toast.makeText(
                                activity,
                                "Owner registered successfully",
                                Toast.LENGTH_LONG
                            ).show()
                            findNavController().navigate(R.id.action_ownerDetails_to_dashboardTab)

                        } else {
                            Toast.makeText(activity, "Invalid Credentials", Toast.LENGTH_SHORT)
                                .show()
                            Log.d("test owner", response.body().toString())
                        }

                    }

                    override fun onFailure(call: Call<ResponseOwner>, t: Throwable) {
                        Toast.makeText(activity, "Something went wrong", Toast.LENGTH_LONG).show()
                    }
                }
                )


            } else {
                Toast.makeText(activity, "Fill up all the fields", Toast.LENGTH_LONG).show()
            }

        }
        return view;
    }

    private fun isOwnerLoginFinished() {
        val sharedPref = requireActivity().getSharedPreferences("OwnerLogin", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

}