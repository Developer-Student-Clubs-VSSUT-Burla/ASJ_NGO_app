package com.example.asjapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.asjapp.databinding.FragmentCreateNgoBinding
import com.example.asjapp.retrofit.ApiClient
import com.example.asjapp.retrofit.RequestClass
import com.example.asjapp.retrofit.ResponseNgo
import retrofit2.Call
import retrofit2.Callback


private var _binding:  FragmentCreateNgoBinding? = null
private val binding get() = _binding!!

class Create_Ngo : Fragment()
{
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateNgoBinding.inflate(inflater,container,false)
        val view = binding.root

        binding.backto.setOnClickListener{
            findNavController().navigate(R.id.action_create_Ngo_to_dashboardTab)
        }

        binding.creatNgoButton.setOnClickListener()
        {
            if(binding.founder.text.toString().isNotEmpty() &&
                binding.ngoname.text.toString().isNotEmpty() &&
                binding.location.text.toString().isNotEmpty() &&
                binding.contact.text.toString().isNotEmpty() &&
                binding.founder.text.toString().isNotEmpty() &&
                binding.founder.text.toString().isNotEmpty()
            )
            {
                val owner = binding.founder.text.toString()
                val name = binding.ngoname.text.toString()
                val location = binding.location.text.toString()
                val contact = binding.contact.text.toString()
                val tagline = binding.tagline.text.toString()
                val desc = binding.description.text.toString()

                val ngo = RequestClass(desc = desc, location = location, name = name, ngo_owner = owner,tagline = tagline, contact = contact )

                val ngoResponseCall = ApiClient.userService.postNgo(ngo)

                ngoResponseCall.enqueue(object : Callback<ResponseNgo>
                {
                    override fun onResponse(
                        call: retrofit2.Call<ResponseNgo>,
                        response: retrofit2.Response<ResponseNgo>
                    ) {
                        if(response.isSuccessful)
                        {
                            Log.d("test", response.body().toString())
                            Toast.makeText(activity,"Data submitted successfully",Toast.LENGTH_LONG).show()
                        }
                        else {
                            Toast.makeText(activity, "Invalid Credentials", Toast.LENGTH_SHORT)
                                .show()
                            Log.d("test", response.body().toString())
                        }
                    }
                    override fun onFailure(call: Call<ResponseNgo>, t: Throwable) {
                        Toast.makeText(activity,"Something went wrong",Toast.LENGTH_LONG).show()
                    }
                }
              )
              findNavController().navigate(R.id.action_create_Ngo_to_dashboardTab)

            }
            else
            {
                Toast.makeText(activity,"Fill up all the fields",Toast.LENGTH_LONG).show()
            }

        }
        return view
    }
}