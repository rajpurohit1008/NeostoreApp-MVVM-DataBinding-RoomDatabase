package com.rajpurohit.neostoreapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductModel (
    @SerializedName("status")
    @Expose
    var status: Int? = null,
    @SerializedName("data")
    @Expose
    var data: Data? = null
)
