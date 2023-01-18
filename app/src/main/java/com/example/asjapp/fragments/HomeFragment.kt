package com.example.asjapp.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asjapp.R
import com.example.asjapp.databinding.FragmentHomeBinding
import com.example.asjapp.recyclerView.HomeCardsAdapter
import com.example.asjapp.retrofit.ApiClient
import retrofit2.HttpException
import java.io.IOException

private lateinit var homeBinding: FragmentHomeBinding

private lateinit var HomeCardsAdapter: HomeCardsAdapter
private val binding get() = homeBinding!!
const val TAG = "homeBinding"


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root


        lifecycleScope.launchWhenCreated {
            val response = try {
                ApiClient.userService.getNgos()
            }catch (e:IOException){
                Log.e(TAG,"IOException, you might not have Internet Connection")
                return@launchWhenCreated
            }catch (e:HttpException){
                Log.e(TAG,"HttpException,unexpected response")
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body()!=null){
                Log.d("Response_List", response.body()!!.toString())
                HomeCardsAdapter.ngos=response.body()!!
            }
            else{
                Log.e(TAG,"Response Not successful")
            }
        }
        setupRecyclerView()
        return view
    }

    private fun setupRecyclerView()= binding.ngoCards.apply {
        HomeCardsAdapter= HomeCardsAdapter()
        adapter= HomeCardsAdapter
        layoutManager=LinearLayoutManager(context)

    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        homeBinding = null
//    }
}
