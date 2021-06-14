package com.rajpurohit.neostoreapp.viewactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rajpurohit.neostoreapp.R
import com.rajpurohit.neostoreapp.adapter.AddressAdapter
import com.rajpurohit.neostoreapp.model.mycartmodel.Mycartcurd
import com.rajpurohit.neostoreapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddresslistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addresslist)
        val addressData = intent.getStringExtra("address")
        Toast.makeText(this, "$addressData", Toast.LENGTH_SHORT).show()
        val addressArrayList = ArrayList<String>()
        addressArrayList.add(addressData.toString())
        val recyclerView:RecyclerView = findViewById(R.id.address_recycleview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AddressAdapter(this,addressArrayList)
        val placeorder = findViewById<Button>(R.id.orderplace)
        placeorder.setOnClickListener {
 Toast.makeText(this, "order place working", Toast.LENGTH_SHORT).show()
   val getsharedToken = getSharedPreferences("My_Token", MODE_PRIVATE)
   val AccessToken3  = getsharedToken.getString("Access_Token","not Fount")
   val callMyorder = ApiService.myCartCURD().postMyOrder(AccessToken3,addressData)
   callMyorder.enqueue(object : Callback<Mycartcurd> {
       override fun onResponse(call: Call<Mycartcurd>, response: Response<Mycartcurd>
       ) {
           Toast.makeText(this@AddresslistActivity, "${response.body()?.userMsg}", Toast.LENGTH_SHORT).show()
       }

       override fun onFailure(call: Call<Mycartcurd>, t: Throwable) {
           Toast.makeText(this@AddresslistActivity, "address $t", Toast.LENGTH_SHORT).show()
       }

   })
}
}

fun onBackclick(view: View) {
val intentcart = Intent(this, MycartActivity::class.java)
    intentcart.flags = Intent.FLAG_ACTIVITY_NEW_TASK
startActivity(intentcart)
}
fun onClickAddress(view: View) {
val addressintent = Intent(this, AddressActivity::class.java)
addressintent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
startActivity(addressintent)
}
}