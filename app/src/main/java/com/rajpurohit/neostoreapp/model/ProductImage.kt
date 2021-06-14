package com.rajpurohit.neostoreapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductImage(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("product_id")
    @Expose
    var productId: Int? = null,

    @SerializedName("image")
    @Expose
    var image: String? = null,

    @SerializedName("created")
    @Expose
    var created: String? = null,

    @SerializedName("modified")
    @Expose
    var modified: String? = null
)
