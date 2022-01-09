package com.example.asjapp.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.asjapp.R
import com.example.asjapp.recyclerView.GalleryAdapter
import com.example.asjapp.database.UserEntity
import com.example.asjapp.databinding.FragmentProfileBinding
import com.example.asjapp.models.ProfileModel

private var _binding: FragmentProfileBinding? = null
private val binding get() = _binding!!

class ProfileFragment : Fragment() {

    val fields: MutableList<EditText> = mutableListOf()
    lateinit var users: List<UserEntity>
    lateinit var user: UserEntity
    var edit = 1
    val IMAGE_REQUEST_CODE = 1_000
    val sharedviewModel: ProfileModel by activityViewModels()

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

        binding.btLogout.setOnClickListener {
            findNavController().navigate(R.id.action_tabbedFragment_to_loginFragment)
            isLoginFinished()
        }

        val adapter2 = GalleryAdapter(galleryPics)
        binding.gallery.adapter = adapter2

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        lifecycleScope.launch{
//            context?.let {
//
//                users= UserDatabase(it).getUserDao().getUser()
//
//                if(users!=null) {
//                    user=users[0]
//
//                    if (user != null) {
//                        withContext(Dispatchers.Main)
//                        {
//                            evName.setText(user.title)
//                            evEmail.setText(user.email)
//                            evBio.setText(user.bio)
//                        }
//                    }
//                }
//                else
//                {
//                    withContext(Dispatchers.Main)
//                    {
//                        evName.setText("Enter Name")
//                        evEmail.setText("Enter Email")
//                        evBio.setText("Enter Bio")
//                    }
//                }
//            }
//        }

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

//                val updatedUser= UserEntity(user.id,evName.text.toString(),evEmail.text.toString(),evBio.text.toString())
//                lifecycleScope.launch {
//                    context?.let{
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
        editor.putBoolean("Finished", false)
        editor.apply()
    }
}