package com.pkpanda.ngofinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class application_activity_prachi : AppCompatActivity() {
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<applicants_prachi>
    lateinit var imagepfp: Array<Int>
    lateinit var volname: Array<String>
    lateinit var volmail:Array<String>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application_prachi)


        imagepfp=arrayOf(R.drawable.p1_prachi,
                         R.drawable.p2_prachi,
                         R.drawable.p1_prachi,
                         R.drawable.p2_prachi,
                         R.drawable.p1_prachi,
                         R.drawable.p2_prachi,
                         R.drawable.p1_prachi,
                         R.drawable.p2_prachi,
                         R.drawable.p1_prachi,
                         R.drawable.p2_prachi)
        volname=arrayOf(
            "Name Of the applicant1",
            "Name Of the applicant2",
            "Name Of the applicant3",
            "Name Of the applicant4",
            "Name Of the applicant5",
            "Name Of the applicant6",
            "Name Of the applicant7",
            "Name Of the applicant8",
            "Name Of the applicant9",
            "Name Of the applicant10"
        )
        volmail=arrayOf(
            "abc@gmail.com",
            "def@gmail.com",
            "ghi@gmail.com",
            "jkl@gmail.com",
            "mno@gmail.com",
            "pqr@gmail.com",
            "stu@gmail.com",
            "vwx@gmail.com",
            "yza@gmail.com",
            "bcd@gmail.com"
        )
        newRecyclerView=findViewById(R.id.recycler_view)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)
        newArrayList= arrayListOf<applicants_prachi>()
        getUserdata()
    }

    private fun getUserdata() {
        for (i in imagepfp.indices){
            val vol= applicants_prachi(imagepfp[i],volname[i],volmail[i])
            newArrayList.add(vol)
        }
        newRecyclerView.adapter= adapter_prachi(newArrayList)
    }
}