package com.rajpurohit.neostoreapp.viewactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rajpurohit.neostoreapp.R
import com.rajpurohit.neostoreapp.adapter.MyOrderAdapter
import com.rajpurohit.neostoreapp.model.order.MyOrderList
import com.rajpurohit.neostoreapp.model.order.OrderDataId
import com.rajpurohit.neostoreapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyorderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myorder)
        val myorderRecyclerView:RecyclerView = findViewById(R.id.myorder_recycler)
        myorderRecyclerView.layoutManager = LinearLayoutManager(this)
        val getsharedToken = getSharedPreferences("My_Token", MODE_PRIVATE)
        val myOrderToken  = getsharedToken.getString("Access_Token","not Fount")
        val callMyorder = ApiService.myCartCURD().getMyOrderList(myOrderToken)
        callMyorder.enqueue(object : Callback<OrderDataId> {
            override fun onResponse(call: Call<OrderDataId>, response: Response<OrderDataId>) {
                if (response.isSuccessful) {
                    myorderRecyclerView.adapter =
                        MyOrderAdapter(this@MyorderActivity, response.body()?.data)
                } else {
                    Toast.makeText(
                        this@MyorderActivity,
                        "${response.body()?.status}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }

            override fun onFailure(call: Call<OrderDataId>, t: Throwable) {
                Toast.makeText(this@MyorderActivity, "$t", Toast.LENGTH_SHORT).show()
            }
        })


    }

    fun backMyorder(view: View) {
        onBackPressed()
    }
}