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
import com.example.asjapp.database.SessionManager
import com.example.asjapp.database.UserDatabase
import com.example.asjapp.database.UserEntity
import com.example.asjapp.databinding.FragmentNgoBinding
import com.example.asjapp.recyclerView.NGOCardsAdapter
import com.example.asjapp.retrofit.ApiClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

private lateinit var ngoBinding: FragmentNgoBinding
private lateinit var NGOCardsAdapter:NGOCardsAdapter
private val binding get() = ngoBinding!!


class NGOFragment : Fragment() {

    private lateinit var users: List<UserEntity>
    private lateinit var user: UserEntity
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ngoBinding = FragmentNgoBinding.inflate(inflater, container, false)
        val view = binding.root


        sessionManager=SessionManager(requireContext())
        lifecycleScope.launchWhenCreated {
            val response = try {
                Log.d("Response_Subs_List", sessionManager.fetchAuthToken().toString())
                ApiClient.userService.getProfile(token = "Bearer ${sessionManager.fetchAuthToken()}")
            }catch (e:IOException){
                Log.e(TAG,"IOException, you might not have Internet Connection")
                return@launchWhenCreated
            }catch (e:HttpException){
                Log.e(TAG,"HttpException,unexpected response")
                return@launchWhenCreated
            }

            if(response.isSuccessful && response.body()!=null){

                NGOCardsAdapter.subNgo=response.body()!!.subscribedNgos
            }
            else{
                Log.e("TAG_Error","Response Not Successful")
            }
        }
        setupRecyclerView()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GlobalScope.launch{
            context?.let {
                users= UserDatabase(it).getUserDao().getUser()
                user=users.last()
            }
        }
    }
    private fun setupRecyclerView()= binding.ngoCards.apply {
        NGOCardsAdapter= NGOCardsAdapter()
        adapter=NGOCardsAdapter
        layoutManager=LinearLayoutManager(context)
    }


}