package com.example.asjapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asjapp.R
import com.example.asjapp.databinding.FragmentNgoBinding
import com.example.asjapp.recyclerView.NGOCardsAdapter
import com.example.asjapp.retrofit.ApiClient
import com.example.asjapp.retrofit.Ngo
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

private lateinit var ngoBinding: FragmentNgoBinding
private lateinit var NGOCardsAdapter:NGOCardsAdapter
private val binding get() = ngoBinding!!


class NGOFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ngoBinding = FragmentNgoBinding.inflate(inflater, container, false)
        val view = binding.root

        lifecycleScope.launchWhenCreated {
            val response = try {
                ApiClient.userService.getOwnNgos()
            }catch (e:IOException){
                Log.e(TAG,"IOException, you might not have Internet Connection")
                return@launchWhenCreated
            }catch (e:HttpException){
                Log.e(TAG,"HttpException,unexpected response")
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body()!=null){
                NGOCardsAdapter.ownngos= response.body()!!
            }
            else{
                Log.e(TAG,"Response Not Successful")
            }
        }
        setupRecyclerView()
        return view
    }

    private fun setupRecyclerView()= binding.ngoCards.apply {
        NGOCardsAdapter=NGOCardsAdapter()
        adapter= NGOCardsAdapter
        layoutManager= LinearLayoutManager(context)

    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        ngoBinding = null
//    }
}