package com.rajpurohit.neostoreapp.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rajpurohit.neostoreapp.model.LoginModel
import com.rajpurohit.neostoreapp.network.ApiService
import com.rajpurohit.neostoreapp.viewactivity.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(application: Application) :AndroidViewModel(application) {
   lateinit var registerLiveData : MutableLiveData<LoginModel>
   init {
       registerLiveData = MutableLiveData()
   }

   fun getregisterLiveData():MutableLiveData<LoginModel>{
       return registerLiveData
   }


    fun registerUser(context: Context,firstName:String, lastName:String,email:String,passWord:String, confirmPass:String,gendr:String,phoneNo:String){
        val registerData = ApiService.registerUserInfo().registerUserData(firstName,lastName, email,passWord,confirmPass,gendr,phoneNo)
       // Toast.makeText(getApplication(), "$firstName $lastName ${email} $passWord $confirmPass $gendr $phoneNo ", Toast.LENGTH_SHORT).show()
        registerData?.enqueue(object : Callback<LoginModel?> {
            override fun onResponse(call: Call<LoginModel?>, response: Response<LoginModel?>) {
                // Toast.makeText(applicationContext, "successfull but not 200", Toast.LENGTH_SHORT).show()

                Toast.makeText(getApplication(), "${response.body()?.userMsg}", Toast.LENGTH_SHORT).show()
               if (response.isSuccessful){
                   registerLiveData.value = response.body()
               }
            }

            override fun onFailure(call: Call<LoginModel?>, t: Throwable) {
                Toast.makeText(getApplication(), "something wrong $t", Toast.LENGTH_SHORT).show()
            }
        })
    }
}