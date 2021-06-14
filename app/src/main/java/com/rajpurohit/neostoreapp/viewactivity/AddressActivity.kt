package com.rajpurohit.neostoreapp.viewactivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rajpurohit.neostoreapp.R
import com.rajpurohit.neostoreapp.model.mycartmodel.Mycartcurd
import com.rajpurohit.neostoreapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)
        val add = findViewById<EditText>(R.id.ed_address)
        val city = findViewById<EditText>(R.id.ed_city)
        val landmark = findViewById<EditText>(R.id.ed_landmark)
        val  zip = findViewById<EditText>(R.id.ed_zip)
        val state = findViewById<EditText>(R.id.ed_state)
        val country = findViewById<EditText>(R.id.ed_country)
        val place_order = findViewById<Button>(R.id.btn_place_order)
        place_order.setOnClickListener(){

           val address = add.text.toString().trim()
            val landmarks = landmark.text.toString().trim()
            val citys = city.text.toString().trim()
            val states = state.text.toString().trim()
            val zips = zip.text.toString().trim()
            val countrys = country.text.toString().trim()
            if (add.length() == 0 || landmark.length() == 0 || city.length() == 0 || zip.length() == 0 || country.length() == 0) {
                if (address.length == 0) {
                    add.requestFocus()
                    add.error = "FIELD CANNOT BE EMPTY"
                }
                if (landmarks.length == 0) {
                    landmark.requestFocus()
                    landmark.error = "FIELD CANNOT BE EMPTY"
                }
                if (citys.length == 0) {
                    city.requestFocus()
                    city.error = "FIELD CANNOT BE EMPTY"
                }
                if (states.length == 0) {
                    state.requestFocus()
                    state.error = "FIELD CANNOT BE EMPTY"
                }
                if (zips.length == 0) {
                    zip.requestFocus()
                    zip.error = "FIELD CANNOT BE EMPTY"
                }
                if (countrys.length == 0) {
                    country.requestFocus()
                    country.error = "FIELD CANNOT BE EMPTY"
                }
            } else {
                val sharedPreferences = getSharedPreferences("My_Token", MODE_PRIVATE)
                val sharedToken = sharedPreferences.edit()
                sharedToken.putString("Address", address)
                sharedToken.commit()

                val addListintent = Intent(this, AddresslistActivity::class.java)
                addListintent.putExtra("address",address)
                addListintent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(addListintent)

               /* Toast.makeText(this, "order place working", Toast.LENGTH_SHORT).show()
                val getsharedToken = getSharedPreferences("My_Token", MODE_PRIVATE)
                val AccessToken3  = getsharedToken.getString("Access_Token","not Fount")
                val callMyorder = ApiService.myCartCURD().postMyOrder(AccessToken3,address)
                callMyorder.enqueue(object : Callback<Mycartcurd> {
                    override fun onResponse(
                        call: Call<Mycartcurd>,
                        response: Response<Mycartcurd>
                    ) {
                        Toast.makeText(
                            this@AddressActivity,
                            "${response.body()?.userMsg}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onFailure(call: Call<Mycartcurd>, t: Throwable) {
                        Toast.makeText(this@AddressActivity, "address $t", Toast.LENGTH_SHORT).show()
                    }

                })
*/
            }
        }
    }
    fun addressBack(view: View) {
         onBackPressed()
     }
}