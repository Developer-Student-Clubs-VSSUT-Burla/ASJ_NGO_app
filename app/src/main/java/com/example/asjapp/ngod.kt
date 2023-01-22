package com.example.asjapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.asjapp.database.UserDatabase
import com.example.asjapp.database.UserEntity
import com.example.asjapp.databinding.FragmentNgodBinding
import com.example.asjapp.fragments.TAG
import com.example.asjapp.login.token
import com.example.asjapp.retrofit.ApiClient
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException


private var _binding: FragmentNgodBinding? = null
private val binding get() = _binding!!

class ngod : Fragment() {
    private lateinit var users: List<UserEntity>
    private lateinit var user: UserEntity
    private lateinit var id: String
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
//                        binding.ngoDetailsName.text= response.body()!![0].name
//                        binding.ngoDetailsLoc.text=response.body()!![0].location
//                        binding.ngoDetailsTagline.text=response.body()!![0].tagline
//                        binding.ngoStartedAt.text=response.body()!![0].startedAt.slice(0..9)
//                        binding.ngoDetails.text=response.body()!![0].desc


                    } else {
                        Log.e("TAG_Error", "Response Not Successful")
                    }
                }

                Log.d("OwnerId", user.uId.toString())
            }
        }




        // Inflate the layout for this fragment
        return view
    }

}