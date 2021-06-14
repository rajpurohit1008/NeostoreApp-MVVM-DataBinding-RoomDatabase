package com.rajpurohit.neostoreapp.model.fetchaccount;

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Datafetch (
    @SerializedName("user_data")
    var userData: UserData? = null,

    @SerializedName("product_categories")
    var productCategories:List<ProductCategory>? = null,

    @SerializedName("total_carts")
    var totalCarts: Int? = null,

    @SerializedName("total_orders")
    var totalOrders: Int? = null
)
