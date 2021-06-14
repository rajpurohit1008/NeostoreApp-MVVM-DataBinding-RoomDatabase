package com.rajpurohit.neostoreapp.viewmodel

import androidx.lifecycle.ViewModel
import com.rajpurohit.neostoreapp.model.fetchaccount.FetchDetailModel
import com.rajpurohit.neostoreapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(token :String): ViewModel() {
    lateinit var fetchuserdata:FetchDetailModel
    val callFetchDetailModel = ApiService.registerUserInfo().getfetchAccountDetail(token)
    fun getfetchDetai() = callFetchDetailModel.enqueue(object : Callback<FetchDetailModel> {
        override fun onResponse(call: Call<FetchDetailModel>, response: Response<FetchDetailModel>) {
            fetchuserdata =response.body()!!
        }

        override fun onFailure(call: Call<FetchDetailModel>, t: Throwable) {
        }
    })
}