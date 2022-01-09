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
import com.example.asjapp.R
import com.example.asjapp.database.UserDatabase
import com.example.asjapp.database.UserEntity
import com.example.asjapp.databinding.FragmentRegisterBinding
import com.example.asjapp.retrofit.ApiClient
import com.example.asjapp.retrofit.SignupResponse
import com.example.asjapp.retrofit.UserRegister
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private var _binding: FragmentRegisterBinding? = null
private val binding get() = _binding!!

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.Continue.setOnClickListener{
            if(binding.fullName.text.toString().isNotEmpty()
                &&binding.number.text.toString().isNotEmpty()
                &&binding.email.text.toString().isNotEmpty()
                &&binding.password.text.toString().isNotEmpty()){

                var name= binding.fullName.text.toString()
                var uname= binding.number.text.toString()
                var email = binding.email.text.toString()
                var pass= binding.password.text.toString()
                var user = UserRegister(name,uname,email,pass)
                var signupResponseCall = ApiClient.userService.registerUser(user)

                signupResponseCall.enqueue(object : Callback<SignupResponse?> {
                    override fun onResponse(call: Call<SignupResponse?>, response: Response<SignupResponse?>) {
                        if(response.isSuccessful)
                        {
                            Log.d("test",response.body().toString())

                            GlobalScope.launch {
                                context?.let {
                                    val userDetails = UserEntity(
                                        0,
                                        response.body()?.full_name.toString(),
                                        response.body()?.email.toString(),
                                        response.body()?.token.toString()
                                    )
                                    UserDatabase(it).getUserDao().addUser(userDetails)
                                }
                            }
                            Toast.makeText(context,"Logged in!", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_registerFragment_to_tabbedFragment)
                            isLoginFinished()
                        }
                        else{
                            Toast.makeText(context,"wrong credentials", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<SignupResponse?>, t: Throwable) {
                        Log.d("test",t.toString())
                    }
                })

            }
            else{
                Toast.makeText(activity, "Enter All fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.login.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        return view
    }

    private fun isLoginFinished(){
        val sharedPref = requireActivity().getSharedPreferences("Login", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }
}