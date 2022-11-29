package com.example.asjapp.recyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.asjapp.MainActivity
import com.example.asjapp.R
import com.example.asjapp.retrofit.SubscribedNgo

class NGOCardsAdapter(var ownngos: List<SubscribedNgo>, var email_loggedIn: String) :
    RecyclerView.Adapter<NGOCardsAdapter.NGOViewHolder>() {


    fun getIndexUser(): Int {
        var index: Int = -1;
        for (i in 0 until ownngos.size) {
            if (ownngos[i].ngo_user.email == email_loggedIn) {
                index = i;
                break
            }
        }
        Log.d("Index", index.toString());
        return index
    }

    var index = getIndexUser()


    var ngoName = Array<String>(if(index!=-1) ownngos[index].ngo_member!!.size else 1) { "Demo" }
    var ngoloc = Array<String>(if(index!=-1) ownngos[index].ngo_member!!.size else 1) { "Demo" }
    var ngotag = Array<String>(if(index!=-1) ownngos[index].ngo_member!!.size else 1) { "Demo" }



    private fun setData() {
        var index = getIndexUser();



        if (index != -1) {
            for (i in ownngos[index].ngo_member!!.indices) {
                ngoName[i] = (ownngos[index].ngo_member?.get(i)!!.name)
                ngoloc[i] = (ownngos[index].ngo_member?.get(i)!!.location)
                ngotag[i] = (ownngos[index].ngo_member?.get(i)!!.tagline)

            }
            Log.d("Test_1",ngoName.toString())

        }
        else{
//            Toast.makeText((),"No NGO Available",Toast.LENGTH_SHORT).show();
            Log.d("Test_1","Hello There")

        }


//        Log.d("msg",)

    }

    inner class NGOViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tittle: TextView = itemView.findViewById(R.id.nameOrg)
        var tag: TextView = itemView.findViewById(R.id.tagline)
        var loc: TextView = itemView.findViewById(R.id.details)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NGOViewHolder {

        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.ngo_items_layout, parent, false)
        return NGOViewHolder(v)
    }

    override fun onBindViewHolder(holder: NGOViewHolder, position: Int) {
        setData()
        holder.tittle.text = ngoName[position]
        holder.tag.text = ngotag[position]
        holder.loc.text = ngoloc[position]
    }

    override fun getItemCount() = ngoName.size


}
