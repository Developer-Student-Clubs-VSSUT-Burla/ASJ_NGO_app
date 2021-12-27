package com.pkpanda.ngofinder


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.pkpanda.ngofinder.databinding.ActivityMainBinding
import com.pkpanda.ngofinder.databinding.ActivityProfileDhairyaBinding

class Profile_Dhairya : AppCompatActivity() {

    private var edit=1
    private val fields: MutableList<EditText> = mutableListOf()
    lateinit var binding: ActivityProfileDhairyaBinding
    companion object {
        const val IMAGE_REQUEST_CODE = 1_000;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityProfileDhairyaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        fields.addAll(listOf(binding.evName,binding.evEmail,binding.evBio))

    }

    fun editProfile(view: android.view.View) {
        if(edit==1)
        {
            binding.btEdit.setImageResource(R.drawable.ic_baseline_save_24)

            for(i in fields)
                i.inputType=InputType.TYPE_CLASS_TEXT
        }
        else
        {
            binding.btEdit.setImageResource(R.drawable.ic_baseline_edit_24)
            for(i in fields)
                i.inputType=InputType.TYPE_NULL

            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }

        edit=1-edit
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