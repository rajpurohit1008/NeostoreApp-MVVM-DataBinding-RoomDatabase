package com.rajpurohit.neostoreapp.viewactivity

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.rajpurohit.neostoreapp.R
import com.rajpurohit.neostoreapp.databinding.ActivityMyaccountBinding
import com.rajpurohit.neostoreapp.model.fetchaccount.FetchDetailModel
import com.rajpurohit.neostoreapp.network.ApiService
import com.rajpurohit.neostoreapp.viewmodel.LoginViewmodel
import com.rajpurohit.neostoreapp.viewmodel.MyAccountViewModel
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyaccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_myaccount)
      /*  val fname:TextView = findViewById(R.id.myaccntfirstname)
        val lname:TextView = findViewById(R.id.myaccntlastname)
        val email:TextView = findViewById(R.id.myaccntemail)
        val phoneno:TextView = findViewById(R.id.myaccntphonenumber)
        val dob:TextView = findViewById(R.id.myaccntdob)
        val circleImage:CircleImageView = findViewById(R.id.myaccntimage)
        val editprofile:Button = findViewById(R.id.myaccnteditprofilebtn)*/
        val getsharedToken = getSharedPreferences("My_Token", MODE_PRIVATE)
        val myaccountToken  = getsharedToken.getString("Access_Token","not Fount")

        //MyAccountViewModel
     val viewModelMyaccount = ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(MyAccountViewModel::class.java)
       viewModelMyaccount.getMyaccountprofile(myaccountToken.toString())

        //Databinding
       val myAccountBinding:ActivityMyaccountBinding = DataBindingUtil.setContentView(this,R.layout.activity_myaccount)
        viewModelMyaccount.getmyAccount().observe(this, Observer {
            if (it != null) {
                myAccountBinding.userproflie = it.data?.userData
            } else {
                Toast.makeText(this, "myaccount is null", Toast.LENGTH_SHORT).show()
            }
        })

       /* val callFetchuser = ApiService.registerUserInfo().getfetchAccountDetail(myaccountToken!!)
        callFetchuser.enqueue(object : Callback<FetchDetailModel> {
            override fun onResponse(call: Call<FetchDetailModel>, response: Response<FetchDetailModel>) {
                //Toast.makeText(this@HomeActivity, "Wel Come  ${response.body()?.data?.userData?.username}", Toast.LENGTH_LONG).show()
                fname.text = response.body()?.data?.userData?.firstName
                lname.text = response.body()?.data?.userData?.lastName
                email.text = response.body()?.data?.userData?.email
                phoneno.text = response.body()?.data?.userData?.phoneNo
                dob.text = "${response.body()?.data?.userData?.dob}"
               *//* val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver,"${response.body()?.data?.userData?.profilePic}".toUri())
                circleImage.setImageBitmap(bitmap)*//*
                Toast.makeText(this@MyaccountActivity, "${response.body()?.data?.userData?.profilePic}", Toast.LENGTH_LONG).show()

                Glide.with(this@MyaccountActivity).load(response.body()?.data?.userData?.profilePic).centerCrop().into(circleImage)
            }

            override fun onFailure(call: Call<FetchDetailModel>, t: Throwable) {
                Toast.makeText(this@MyaccountActivity, "something wrong on homeActivity", Toast.LENGTH_SHORT).show()
            }
        })*/


    }

    fun backmyaccount(view: View) {
        onBackPressed()
    }

    fun editonclick(view: View) {
        val intenteditprofile = Intent(this, EditprofileActivity::class.java)
        intenteditprofile.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intenteditprofile)
    }

    fun resetpassword(view: View) {
        val intentresetpassword = Intent(this, ResetpasswordActivity::class.java)
        intentresetpassword.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intentresetpassword)
    }
}