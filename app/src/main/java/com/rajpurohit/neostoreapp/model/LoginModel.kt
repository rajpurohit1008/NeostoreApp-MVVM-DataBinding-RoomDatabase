package com.rajpurohit.neostoreapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.Call

data class LoginModel (
    @SerializedName("status")
    var status: String? = null,

    @SerializedName("data")
    var data: DataLogin,

    @SerializedName("message")
    var message: String? = null,

    @SerializedName("user_msg")
    var userMsg: String? = null
    )


