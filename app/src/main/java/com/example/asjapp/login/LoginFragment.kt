package com.example.asjapp.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.asjapp.retrofit.ApiClient
import com.example.asjapp.retrofit.LoginResponse
import com.example.asjapp.retrofit.UserLogin
import com.example.asjapp.R
import com.example.asjapp.database.UserDatabase
import com.example.asjapp.database.UserEntity
import com.example.asjapp.databinding.FragmentLoginBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Callback

private var _binding: FragmentLoginBinding? = null
private val binding get() = _binding!!

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.Continue.setOnClickListener {

            if (binding.email.text.toString().isNotEmpty() && binding.password.text.toString()
                    .isNotEmpty()
            ) {
                val email = binding.email.text.toString()
                val password = binding.password.text.toString()

                val user = UserLogin(email, password)

                val loginResponseCall = ApiClient.userService.loginUser(user)

                loginResponseCall.enqueue(object : Callback<LoginResponse?> {

                    override fun onResponse(
                        call: retrofit2.Call<LoginResponse?>,
                        response: retrofit2.Response<LoginResponse?>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("test", response.body().toString())
                            GlobalScope.launch {
                                context?.let {
                                    Log.d("TAG", response.body().toString())
                                    val userDetails = UserEntity(
                                        0,
                                        response.body()?.full_name.toString(),
                                        response.body()?.email.toString(),
                                        "Enter bio".toString(),
                                        response.body()?.token.toString()
                                    )
                                    UserDatabase(it).getUserDao().addUser(userDetails)
                                }
                            }
                        findNavController().navigate(R.id.action_loginFragment_to_tabbedFragment)
                        isLoginFinished()

                        } else {
                            Toast.makeText(activity, "Invalid Credentials", Toast.LENGTH_SHORT)
                                .show()
                            Log.d("test", response.body().toString())
                        }
                    }

                    override fun onFailure(call: retrofit2.Call<LoginResponse?>, t: Throwable) {
                        Log.d("test", t.toString())
                    }
                })

            } else {
                when {
                    binding.email.text.toString().isEmpty() && binding.password.text.toString()
                        .isEmpty() -> Toast.makeText(
                        activity,
                        "Enter Email-id and Password",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.password.text.toString().isEmpty() -> Toast.makeText(
                        activity,
                        "Enter Password",
                        Toast.LENGTH_SHORT
                    ).show()
                    else -> Toast.makeText(activity, "Enter Email-id", Toast.LENGTH_SHORT).show()
                }
            }

        }
        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return view
    }


    private fun isLoginFinished() {
        val sharedPref = requireActivity().getSharedPreferences("Login", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

}