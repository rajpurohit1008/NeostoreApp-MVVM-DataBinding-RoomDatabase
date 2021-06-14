package com.rajpurohit.neostoreapp.adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rajpurohit.neostoreapp.R
import com.rajpurohit.neostoreapp.model.order.DataItem
import com.rajpurohit.neostoreapp.model.order.DataItemId
import com.rajpurohit.neostoreapp.viewactivity.OrderidActivity

class MyOrderAdapter (val context: Context, val dataList:List<DataItemId?>?):RecyclerView.Adapter<MyOrderAdapter.MyOrderVH>(){
    inner class MyOrderVH(itemView: View):RecyclerView.ViewHolder(itemView){
        val orderId : TextView = itemView.findViewById(R.id.order_id)
        val orderDate : TextView = itemView.findViewById(R.id.order_date)
        val orderCost : TextView = itemView.findViewById(R.id.order_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyOrderVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.myorder_list,parent,false)
        return MyOrderVH(view)
    }

    override fun onBindViewHolder(holder: MyOrderVH, position: Int) {
        val currentItem = dataList?.get(position)
        holder.orderId.text = currentItem?.id.toString()
        holder.orderDate.text = currentItem?.created.toString()
        holder.orderCost.text = currentItem?.cost.toString()
        holder.itemView.setOnClickListener(){
            val orderidintent = Intent(context,OrderidActivity::class.java)
            orderidintent.putExtra("order_Id","${currentItem?.id.toString()}")
            orderidintent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(orderidintent)
        }

    }

    override fun getItemCount(): Int =dataList!!.size
}