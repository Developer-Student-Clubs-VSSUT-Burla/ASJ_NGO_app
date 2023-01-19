package com.example.asjapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.asjapp.database.UserDatabase
import com.example.asjapp.database.UserEntity
import com.example.asjapp.databinding.FragmentNgodBinding
import com.example.asjapp.login.token
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


private var _binding: FragmentNgodBinding? = null
private val binding get() = _binding!!

class ngod : Fragment() {
    private lateinit var users: List<UserEntity>
    private lateinit var user: UserEntity
    private val args: ngodArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        GlobalScope.launch{
            context?.let {

                users= UserDatabase(it).getUserDao().getUser()

                user=users.last()
                withContext(Dispatchers.Main)
                {

//                    evName.setText(user.name)
//                    evEmail.setText(user.email)
//                    evBio.setText(user.bio)
                }
                Log.d("OwnerId",user.uId.toString())
            }
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ngod, container, false)
    }

}