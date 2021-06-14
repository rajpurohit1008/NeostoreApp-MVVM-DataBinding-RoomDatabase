package com.rajpurohit.neostoreapp.viewactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rajpurohit.neostoreapp.R
import com.rajpurohit.neostoreapp.adapter.OrderIdAdapter
import com.rajpurohit.neostoreapp.model.order.MyOrderList
import com.rajpurohit.neostoreapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderidActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orderid)
        val orderId = intent.getStringExtra("order_Id")
       val getsharedToken = getSharedPreferences("My_Token", MODE_PRIVATE)
        val orderidToken  = getsharedToken.getString("Access_Token","not Fount")
        val ordertool:TextView = findViewById(R.id.detail_title)
        val ordercost:TextView = findViewById(R.id.detail_price)
        ordertool.text = "ORDER ID :${orderId}"
       val orderRecyclerView:RecyclerView = findViewById(R.id.detail_recycler)
        orderRecyclerView.layoutManager = LinearLayoutManager(this)
       val callOrderId = ApiService.myCartCURD().getOrderIdList(orderidToken,orderId?.toInt())
        callOrderId.enqueue(object : Callback<MyOrderList> {
            override fun onResponse(call: Call<MyOrderList>, response: Response<MyOrderList>) {
                if (response.isSuccessful) {
                    val dataOrderList = response.body()?.data
                    ordercost.text = "₹  ${dataOrderList?.cost}"
                    orderRecyclerView.adapter = OrderIdAdapter(
                        this@OrderidActivity,dataOrderList?.order_details)

                }
            }

            override fun onFailure(call: Call<MyOrderList>, t: Throwable) {
                Toast.makeText(this@OrderidActivity, "$t", Toast.LENGTH_LONG).show()
            }
        })

    }

    fun backorderid(view: View) {
        onBackPressed()
    }


}