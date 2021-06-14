package com.rajpurohit.neostoreapp.viewactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.rajpurohit.neostoreapp.R
import com.rajpurohit.neostoreapp.model.LoginModel
import com.rajpurohit.neostoreapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotpasswordActivity : AppCompatActivity() {
    lateinit var forgotprogress: ProgressBar
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgotpassword)
        val emailId :TextView = findViewById(R.id.emailid)
        val submit :Button = findViewById(R.id.Submit)
        submit.setOnClickListener() {
            if (emailId.text.isEmpty()) {
                emailId?.requestFocus()
                emailId?.setError("Please Enter Email")
            } else if (!emailId.text.matches(Regex(emailPattern))) {
                emailId?.requestFocus()
                emailId?.setError("Please Enter Valid Email Id")
            }else{
                val forgotCall = ApiService.registerUserInfo().forgotPassword(emailId.text.toString())
                forgotCall.enqueue(object : Callback<LoginModel> {
                    override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                        Toast.makeText(applicationContext, "${response.body()?.userMsg}", Toast.LENGTH_SHORT).show()

                    }

                    override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                        Toast.makeText(applicationContext, "Somrthing Wrong in Forgot$t", Toast.LENGTH_SHORT).show()
                    }

                })
            }
        }

    }
}