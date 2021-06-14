package com.rajpurohit.neostoreapp.model.fetchaccount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

data class ProductCategory (
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("icon_image")
    @Expose
    var iconImage: String? = null,

    @SerializedName("created")
    var created: String? = null,

    @SerializedName("modified")
    var modified: String? = null
    )
