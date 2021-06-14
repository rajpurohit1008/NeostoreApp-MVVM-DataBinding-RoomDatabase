package com.rajpurohit.neostoreapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rajpurohit.neostoreapp.R
import com.rajpurohit.neostoreapp.model.order.OrderDetails

class OrderIdAdapter(val context:Context,val orderidList:List<OrderDetails>?):RecyclerView.Adapter<OrderIdAdapter.OderIdVH>() {
    inner class OderIdVH(itemView: View) :RecyclerView.ViewHolder(itemView){
        val orderidImage :ImageView = itemView.findViewById(R.id.orderid_image)
        val orderidtitle :TextView = itemView.findViewById(R.id.orderid_title)
        val orderidcategory :TextView = itemView.findViewById(R.id.orderid_category)
        val orderidqty :TextView = itemView.findViewById(R.id.orderid_qty)
        val orderidcost :TextView = itemView.findViewById(R.id.orderid_cost)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OderIdVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.orderid_list,parent,false)
       return  OderIdVH(view)
    }

    override fun onBindViewHolder(holder: OderIdVH, position: Int) {
        val currentItem = orderidList?.get(position)
        holder.orderidtitle.text = currentItem?.prod_name
        holder.orderidcategory.text = currentItem?.prod_cat_name
        holder.orderidcost.text = "₹ ${currentItem?.total}"
        holder.orderidqty.text = " QTY : ${currentItem?.quantity}"
        Glide.with(context).load(currentItem?.prod_image).centerCrop().into(holder.orderidImage)
    }

    override fun getItemCount(): Int = orderidList?.size!!
}