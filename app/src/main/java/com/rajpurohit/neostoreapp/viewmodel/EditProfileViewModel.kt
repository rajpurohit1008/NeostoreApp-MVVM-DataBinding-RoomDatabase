package com.rajpurohit.neostoreapp.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.rajpurohit.neostoreapp.model.LoginModel
import com.rajpurohit.neostoreapp.network.ApiService
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfileViewModel(application: Application) :AndroidViewModel(application) {
   private val editProfileLiveData:MutableLiveData<LoginModel>
   init {
       editProfileLiveData=MutableLiveData()
   }
    fun getEditProfile():MutableLiveData<LoginModel> = editProfileLiveData
fun makeApiCall(editprofileToken:String,fname:String,lname:String,email:String,editdob:String,phone:String){

    val callEditprofile = ApiService.registerUserInfo().editProfile(editprofileToken, fname, lname, email, editdob,"null", phone)
    callEditprofile?.enqueue(object : Callback<LoginModel?> {
        override fun onResponse(call: Call<LoginModel?>, response: Response<LoginModel?>) {
            if (response.isSuccessful){
                editProfileLiveData.value = response.body()
               /* intent = Intent(this@EditprofileActivity, MyaccountActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)*/
            }else {
                Log.d("annu", "response fail")
                try {
                    Log.d("annu", "try block")
                    val jObjError = JSONObject(response.errorBody()!!.string())
                    Log.e("annu", jObjError.getString("user_msg"))
                    Toast.makeText(getApplication(), jObjError.getString("user_msg"), Toast.LENGTH_SHORT)
                        .show()

                } catch (e: Exception) {
                    Log.d("annu", "catch block")
                    Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        override fun onFailure(call: Call<LoginModel?>, t: Throwable) {
            Toast.makeText(getApplication(), "EditProfile $t", Toast.LENGTH_SHORT).show()
        }
    })
}
}