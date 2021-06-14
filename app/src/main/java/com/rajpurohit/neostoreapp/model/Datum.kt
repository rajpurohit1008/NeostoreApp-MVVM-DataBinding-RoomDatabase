package com.rajpurohit.neostoreapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Datum(
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("product_category_id")
    var productCategoryId: Int? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("producer")
    var producer: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("cost")
    var cost: Int? = null,

    @SerializedName("rating")
    var rating: Int? = null,

    @SerializedName("view_count")
    var viewCount: Int? = null,

    @SerializedName("created")
    var created: String? = null,

    @SerializedName("modified")
    var modified: String? = null,

    @SerializedName("product_images")
    var productImages: String? = null
)
