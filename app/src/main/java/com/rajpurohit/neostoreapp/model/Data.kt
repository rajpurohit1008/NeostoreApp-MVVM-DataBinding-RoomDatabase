package com.rajpurohit.neostoreapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data (
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("product_category_id")
    @Expose
    var productCategoryId: Int? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("producer")
    @Expose
    var producer: String? = null,

    @SerializedName("description")
    @Expose
    var description: String? = null,

    @SerializedName("cost")
    @Expose
    var cost: Int? = null,

    @SerializedName("rating")
    @Expose
    var rating: Int? = null,

    @SerializedName("view_count")
    @Expose
    var viewCount: Int? = null,

    @SerializedName("created")
    @Expose
    var created: String? = null,

    @SerializedName("modified")
    @Expose
    var modified: String? = null,

    @SerializedName("product_images")
    @Expose
    var productImages: List<ProductImage>
)
