package com.pkpanda.ngofinder


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.pkpanda.ngofinder.databinding.ActivityMainBinding
import com.pkpanda.ngofinder.databinding.ActivityProfileDhairyaBinding
import com.pkpanda.ngofinder.models.ProfileDhairyaModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.security.Provider


class Profile_Dhairya : AppCompatActivity() {

    private val fields: MutableList<EditText> = mutableListOf()
    private lateinit var binding:ActivityProfileDhairyaBinding
    lateinit var viewModel:ProfileDhairyaModel


    companion object {
        const val IMAGE_REQUEST_CODE = 1_000;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityProfileDhairyaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        fields.addAll(listOf(binding.evName,binding.evEmail,binding.evBio))



        viewModel= ViewModelProvider(this).get(ProfileDhairyaModel::class.java)


        viewModel.getedit().observe(this,{

            if(it==1)
            {
                binding.btEdit.setImageResource(R.drawable.ic_baseline_save_24)

                for(i in fields) {
                    i.inputType = InputType.TYPE_CLASS_TEXT
                }
            }
            else
            {
                binding.btEdit.setImageResource(R.drawable.ic_baseline_edit_24)
                for(i in fields) {
                    i.customSelectionActionModeCallback = object : ActionMode.Callback {
                        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
                            return false
                        }
                        override fun onDestroyActionMode(mode: ActionMode) {}
                        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
                            return false
                        }
                        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
                            return false
                        }
                    }
                    i.inputType = InputType.TYPE_NULL
                }

                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

                if(imm.isActive)
                    imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            }
        })

    }

    fun editProfile(view: android.view.View) {
        viewModel.toggle()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            binding.ivPfp.setImageURI(data?.data)
        }
    }

    fun pickImageFromGallery(view: android.view.View) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

}