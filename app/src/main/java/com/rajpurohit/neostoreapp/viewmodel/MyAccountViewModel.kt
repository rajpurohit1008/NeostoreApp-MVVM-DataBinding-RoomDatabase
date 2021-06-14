package com.rajpurohit.neostoreapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rajpurohit.neostoreapp.model.fetchaccount.FetchDetailModel
import com.rajpurohit.neostoreapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyAccountViewModel(application: Application) :AndroidViewModel(application) {
  private  lateinit var myAccountData:MutableLiveData<FetchDetailModel>

  init {
      myAccountData = MutableLiveData()
  }
    fun getmyAccount():MutableLiveData<FetchDetailModel> = myAccountData
    fun getMyaccountprofile(myaccountToken:String){
        val callFetchuser = ApiService.registerUserInfo().getfetchAccountDetail(myaccountToken)
        callFetchuser.enqueue(object : Callback<FetchDetailModel> {
            override fun onResponse(call: Call<FetchDetailModel>, response: Response<FetchDetailModel>) {
                Toast.makeText(getApplication(), "  ${response.body()?.data?.userData?.username}", Toast.LENGTH_LONG).show()
                if (response.isSuccessful) {
                    myAccountData.value = response.body()
                }else{
                    myAccountData.value = null
                }

            }

            override fun onFailure(call: Call<FetchDetailModel>, t: Throwable) {
                Toast.makeText(getApplication(), "something wrong on myAccountActivity", Toast.LENGTH_SHORT).show()
            }
        })
    }
}