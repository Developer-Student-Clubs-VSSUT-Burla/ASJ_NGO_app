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
import com.example.asjapp.database.UserDatabase
import com.example.asjapp.database.UserEntity
import com.example.asjapp.databinding.FragmentNgoBinding
import com.example.asjapp.recyclerView.NGOCardsAdapter
import com.example.asjapp.retrofit.ApiClient
import com.example.asjapp.retrofit.Ngo
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.Dispatchers
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
                Log.d("Response_List",response.body()!![0].ngo_user.username)

                binding.ngoCards.apply {
                    NGOCardsAdapter = NGOCardsAdapter(response.body()!!,users.last().email)
                    adapter = NGOCardsAdapter
                    layoutManager = LinearLayoutManager(context)
                }
            }
            else{
                Log.e(TAG,"Response Not Successful")
            }
        }
//        setupRecyclerView()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GlobalScope.launch{
            context?.let {
                users= UserDatabase(it).getUserDao().getUser()
                user=users.last()
//                withContext(Dispatchers.Main)
//                {
//                    evName.setText(user.name)
//                    evEmail.setText(user.email)
//                    evBio.setText(user.bio)
//                }
            }
        }
    }
////    private fun setupRecyclerView()= binding.ngoCards.apply {
//
//
//    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        ngoBinding = null
//    }
}