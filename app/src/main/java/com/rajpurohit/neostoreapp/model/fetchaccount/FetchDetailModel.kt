package com.rajpurohit.neostoreapp.model.fetchaccount;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

data class FetchDetailModel (
    @SerializedName("status")
    @Expose
    var status: Int? = null,

    @SerializedName("data")
    @Expose
    var data: Datafetch? = null
)