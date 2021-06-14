package com.rajpurohit.neostoreapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.rajpurohit.neostoreapp.R

class AddressAdapter(val context: Context,val addressList:ArrayList<String>):RecyclerView.Adapter<AddressAdapter.AddressVH>() {
    inner class AddressVH(itemView: View):RecyclerView.ViewHolder(itemView){
val addressRadio:RadioButton = itemView.findViewById(R.id.address_radioButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.save_addresslist,parent,false)
        return AddressVH(view)
    }

    override fun onBindViewHolder(holder: AddressVH, position: Int) {
        holder.addressRadio.text = addressList[position]
    }

    override fun getItemCount(): Int = addressList.size
}