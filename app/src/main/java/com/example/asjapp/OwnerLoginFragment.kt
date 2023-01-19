package com.example.asjapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.asjapp.database.SessionManager
import com.example.asjapp.database.UserDatabase
import com.example.asjapp.database.UserEntity
import com.example.asjapp.databinding.FragmentOwnerLoginBinding
import com.example.asjapp.retrofit.ApiClient
import com.example.asjapp.retrofit.LoginResponse
import com.example.asjapp.retrofit.UserLogin
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private var _binding: FragmentOwnerLoginBinding? = null
private val binding get() = _binding!!


class OwnerLoginFragment : Fragment() {
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOwnerLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        sessionManager = SessionManager(requireContext())
        binding.ContinueBtn.setOnClickListener {

            if (binding.ownerLoginEmail.text.toString()
                    .isNotEmpty() && binding.ownerPassword.text.toString().isNotEmpty()
            ) {
                val ownerEmail = binding.ownerLoginEmail.text.toString()
                val ownerPassword = binding.ownerPassword.text.toString()

                val ngoOwner = UserLogin(ownerEmail, ownerPassword)
                val loginResponseCall = ApiClient.userService.loginNgoOwner(ngoOwner)
                loginResponseCall.enqueue(object : Callback<LoginResponse?> {
                    override fun onResponse(
                        call: Call<LoginResponse?>, response: Response<LoginResponse?>
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
                                }
                            }
                            sessionManager.saveAuthToken(response.body()?.token.toString())
                            Toast.makeText(activity, "Logged In", Toast.LENGTH_SHORT).show()
                            Log.d("Response_User_List", response.body().toString())
                            val action =
                                OwnerLoginFragmentDirections.actionOwnerLoginFragmentToDashboardTab(
                                    response.body()?._id!!.toString()
                                )
                            findNavController().navigate(action)

                        } else {
                            Toast.makeText(activity, "Invalid Credentials", Toast.LENGTH_SHORT)
                                .show()
                            Log.d("test", response.body().toString())

                        }
                    }

                    override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                        Log.d("test", t.toString())
                    }

                })
            }
        }
        return view
    }


}