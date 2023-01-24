@file:Suppress("OverrideDeprecatedMigration")

package com.example.asjapp.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.asjapp.R
import com.example.asjapp.database.SessionManager
import com.example.asjapp.database.UserDatabase
import com.example.asjapp.recyclerView.GalleryAdapter
import com.example.asjapp.database.UserEntity
import com.example.asjapp.databinding.FragmentProfileBinding
import com.example.asjapp.login.token
import com.example.asjapp.models.ProfileModel
import com.example.asjapp.retrofit.ApiClient
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

private var _binding: FragmentProfileBinding? = null
private val binding get() = _binding!!

@Suppress("DEPRECATION")
class ProfileFragment : Fragment() {

    private val fields: MutableList<EditText> = mutableListOf()
    private lateinit var users: List<UserEntity>
    private lateinit var user: UserEntity
    private val IMAGE_REQUEST_CODE = 1_000
    private val sharedviewModel: ProfileModel by activityViewModels()
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        val galleryPics = intArrayOf(
            R.drawable.screen21, R.drawable.screen24, R.drawable.screen18, R.drawable.screen15
        )

        sessionManager= SessionManager(requireContext())
        lifecycleScope.launchWhenCreated {
            val response = try {
                Log.d("Owner Detail token", sessionManager.fetchAuthToken().toString())
                ApiClient.userService.getProfile(token = "Bearer ${sessionManager.fetchAuthToken()}")
            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have Internet Connection")
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException,unexpected response")
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null) {
                evName.setText(response.body()!!.name)
                evEmail.setText(response.body()!!.email)
//                evBio.setText(user.bio)
            }
        }

        binding.btLogout.setOnClickListener {
            GlobalScope.launch{
                context?.let {
                    users= UserDatabase(it).getUserDao().getUser()
                    val token = users.last().token
                    UserDatabase(it).getUserDao().deleteByTokenId(token)
                }
            }

            findNavController().navigate(R.id.action_tabbedFragment_to_loginFragment)
            isLoginFinished()
            Log.d("Login",isLoginFinished().toString())

        }

        val adapter2 = GalleryAdapter(galleryPics)
        binding.gallery.adapter = adapter2

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        fields.addAll(listOf(binding.evName, binding.evEmail, binding.evBio))
        val context = activity

        sharedviewModel.getedit().observe(this.viewLifecycleOwner, {
            if (it == 1) {
                binding.btEdit.setImageResource(R.drawable.ic_baseline_save_24)

                for (i in fields) {
                    i.inputType = InputType.TYPE_CLASS_TEXT
                }
            } else {
                binding.btEdit.setImageResource(R.drawable.ic_baseline_edit_24)
                for (i in fields) {
                    i.customSelectionActionModeCallback = object : ActionMode.Callback {
                        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
                            return false
                        }

                        override fun onDestroyActionMode(mode: ActionMode) {}
                        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
                            return false
                        }

                        override fun onActionItemClicked(
                            mode: ActionMode,
                            item: MenuItem
                        ): Boolean {
                            return false
                        }
                    }
                    i.inputType = InputType.TYPE_NULL

                    val imm = context!!.getSystemService(
                        Context.INPUT_METHOD_SERVICE
                    ) as InputMethodManager

                    if (imm.isActive)
                        imm.hideSoftInputFromWindow(view.windowToken, 0)
                }


//                GlobalScope.launch{
//                    context?.let {
//                        users= UserDatabase(it).getUserDao().getUser()
//                        user=users.last()
//                        val updatedUser= UserEntity(user.id,evName.text.toString(),evEmail.text.toString(),evBio.text.toString(),user.token,user.uId)
//                        UserDatabase(it).getUserDao().updateUser(updatedUser)
//                    }
//                }

            }
        })

        binding.btEdit.setOnClickListener {
            sharedviewModel.toggle()
        }

        binding.btDp.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, IMAGE_REQUEST_CODE)
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            binding.ivPfp.setImageURI(data?.data)
        }
    }

    private fun isLoginFinished() {
        val sharedPref = requireActivity().getSharedPreferences("Login", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        Log.d("LoginCheck","LoginFalse")
        editor.putBoolean("Finished", false)
        editor.apply()

    }
}