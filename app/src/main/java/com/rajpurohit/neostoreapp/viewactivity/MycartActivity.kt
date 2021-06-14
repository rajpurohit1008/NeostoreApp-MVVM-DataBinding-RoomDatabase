package com.rajpurohit.neostoreapp.viewactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rajpurohit.neostoreapp.R
import com.rajpurohit.neostoreapp.adapter.MycartAdapter
import com.rajpurohit.neostoreapp.model.mycartmodel.FetchMycart
import com.rajpurohit.neostoreapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MycartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mycart)
        val myCartrecycleView :RecyclerView = findViewById(R.id.mycart_recycleview)
        val mycarttotal :TextView = findViewById(R.id.mycart_total)

        myCartrecycleView.layoutManager = LinearLayoutManager(this)
        val getsharedToken = getSharedPreferences("My_Token", MODE_PRIVATE)
        val myCartToken  = getsharedToken.getString("Access_Token","not Fount")
        val callmycart = ApiService.myCartCURD().getMycartList(myCartToken)
        callmycart.enqueue(object : Callback<FetchMycart?> {
            override fun onResponse(call: Call<FetchMycart?>, response: Response<FetchMycart?>) {
                Toast.makeText(this@MycartActivity, "${response.body()?.status}", Toast.LENGTH_SHORT).show()
                if (response.isSuccessful) {
                    mycarttotal.text = "₹ ${response.body()?.total.toString()}"
                    val datalist = response.body()?.data
                    myCartrecycleView.adapter =
                        MycartAdapter(applicationContext, datalist, myCartToken)
                }

            }

            override fun onFailure(call: Call<FetchMycart?>, t: Throwable) {
                Toast.makeText(this@MycartActivity, "something wrong in mycart fetchdata $t", Toast.LENGTH_SHORT).show()
            }
        })

    }

    fun backMycart(view: View) {
        onBackPressed()
    }

    fun orderNow(view: View) {
        val addressintent = Intent(this, AddressActivity::class.java)
            addressintent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(addressintent)

    }
}