package com.pkpanda.ngofinder.fragments_adarsh

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.pkpanda.ngofinder.MainActivity_adarsh
import com.pkpanda.ngofinder.R
import com.pkpanda.ngofinder.models.ProfileDhairyaModel
import kotlinx.android.synthetic.main.fragment_ngo__profile.*
import kotlinx.android.synthetic.main.fragment_profile_adarsh.*

class ProfileFragment_Adarsh : Fragment() {

    val fields: MutableList<EditText> = mutableListOf()

    val IMAGE_REQUEST_CODE = 1_000;
    val sharedviewModel: ProfileDhairyaModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_adarsh, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fields.addAll(listOf(evName,evEmail,evBio))
        var context = activity

        sharedviewModel.getedit().observe(this.viewLifecycleOwner,{
            if(it==1)
            {
                btShare.setImageResource(R.drawable.ic_baseline_save_24)

                for(i in fields) {
                    i.inputType = InputType.TYPE_CLASS_TEXT
                }
            }
            else
            {
                btShare.setImageResource(R.drawable.ic_baseline_edit_24)
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

                    val imm = context!!.getSystemService(
                        Context.INPUT_METHOD_SERVICE
                    ) as InputMethodManager

                    if(imm.isActive)
                        imm.hideSoftInputFromWindow(view.windowToken, 0);
                }

            }
        })

        btEdit.setOnClickListener {
            sharedviewModel.toggle()
        }

        btDp.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, IMAGE_REQUEST_CODE)
        }

    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            ivPfp.setImageURI(data?.data)
        }
    }

}