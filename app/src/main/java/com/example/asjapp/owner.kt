package com.example.asjapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.asjapp.database.SessionManager

import com.example.asjapp.databinding.FragmentOwnerBinding

import com.example.asjapp.fragments.TAG

import com.example.asjapp.retrofit.ApiClient
import retrofit2.HttpException
import java.io.IOException


private var _binding: FragmentOwnerBinding? = null
private val binding get() = _binding!!

class owner : Fragment() {

    private lateinit var sessionManager: SessionManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOwnerBinding.inflate(inflater, container, false)
        val view = binding.root


        sessionManager= SessionManager(requireContext())
        lifecycleScope.launchWhenCreated {
            val response = try {
                Log.d("Owner Detail token", sessionManager.fetchAuthToken().toString())
                ApiClient.userService.getProfile(token = "Bearer ${sessionManager.fetchAuthToken()}")
            }catch (e: IOException){
                Log.e(TAG,"IOException, you might not have Internet Connection")
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.e(TAG,"HttpException,unexpected response")
                return@launchWhenCreated
            }

            if(response.isSuccessful && response.body()!=null){

                Toast.makeText(activity, "Got it", Toast.LENGTH_SHORT).show()
                binding.oname.text = response.body()!!.name.toString()
                binding.oemail.text = response.body()!!.email.toString()
                binding.ousername.text = response.body()!!.username.toString()
                binding.odob.text = response.body()!!.dob.toString()
                binding.ojoineddate.text = response.body()!!.date_joined.toString()
                binding.oaddress.text = response.body()!!.address.toString()
                binding.ocity.text = response.body()!!.city.toString()


            }
            else{
                Log.e("TAG_Error","Response Not Successful")
            }
        }

        return view
    }


}