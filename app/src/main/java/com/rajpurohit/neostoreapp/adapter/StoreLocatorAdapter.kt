package com.rajpurohit.neostoreapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rajpurohit.neostoreapp.R

class StoreLocatorAdapter(context: Context):RecyclerView.Adapter<StoreLocatorAdapter.StoreLocatorVH>() {
    inner class StoreLocatorVH(itemView: View):RecyclerView.ViewHolder(itemView){
        val storeName:TextView = itemView.findViewById(R.id.store_name)
        val storeAddress:TextView = itemView.findViewById(R.id.store_address)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreLocatorVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.storelocator_list,parent,false)
        return  StoreLocatorVH((view))
    }

    override fun onBindViewHolder(holder: StoreLocatorVH, position: Int) {

    }

    override fun getItemCount(): Int = 3
}