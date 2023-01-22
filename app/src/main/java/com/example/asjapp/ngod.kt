package com.example.asjapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.asjapp.database.UserDatabase
import com.example.asjapp.database.UserEntity
import com.example.asjapp.fragments.TAG
import com.example.asjapp.retrofit.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asjapp.adapters.OwnerNgoAdapter
import com.example.asjapp.databinding.FragmentNgodBinding
import com.example.asjapp.ownerNgo.OwnerOwnNgoAdapter

private var _binding: FragmentNgodBinding? = null
private lateinit var OwnerOwnNgoAdapter: OwnerOwnNgoAdapter
private val binding get() = _binding!!

class ngod : Fragment() {
    private lateinit var users: List<UserEntity>
    private lateinit var user: UserEntity
    private lateinit var id: String


    lateinit var adapter: RecyclerView.Adapter<OwnerNgoAdapter.ViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNgodBinding.inflate(inflater, container, false)
        val view = binding.root


        GlobalScope.launch {
            context?.let {

                users = UserDatabase(it).getUserDao().getUser()

                user = users.last()
                id = user.uId
                withContext(Dispatchers.Main)
                {
                    val response = try {
                        ApiClient.userService.getNgos(owner = id)
                    } catch (e: IOException) {
                        Log.e(TAG, "IOException, you might not have Internet Connection")
                        return@withContext
                    } catch (e: HttpException) {
                        Log.e(TAG, "HttpException,unexpected response")
                        return@withContext
                    }
                    if (response.isSuccessful && response.body() != null) {
                        OwnerOwnNgoAdapter.ownNgo=response.body()!!

                    } else {
                        Log.e("TAG_Error", "Response Not Successful")
                    }
                }

                Log.d("OwnerId", user.uId.toString())
            }
        }




        // Inflate the layout for this fragment
        binding.recyclengo.apply {
            adapter = OwnerNgoAdapter()
            binding.recyclengo.adapter = adapter
            layoutManager = LinearLayoutManager(context)
        }
        setupRecyclerView()

        return view
    }

    private fun setupRecyclerView()= binding.recyclengo.apply {
        OwnerOwnNgoAdapter = OwnerOwnNgoAdapter()
        adapter= OwnerOwnNgoAdapter
        layoutManager=LinearLayoutManager(context)
    }

}