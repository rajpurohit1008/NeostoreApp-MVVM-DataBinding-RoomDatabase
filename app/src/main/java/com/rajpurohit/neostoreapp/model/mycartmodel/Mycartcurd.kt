package com.rajpurohit.neostoreapp.model.mycartmodel

import com.google.gson.annotations.SerializedName

data class Mycartcurd(
	@SerializedName("total_carts")
	val totalCarts: Int? = null,
	@SerializedName("data")
	val data: Boolean? = null,
	@SerializedName("message")
	val message: String? = null,
	@SerializedName("status")
	val status: Int? = null,
	@SerializedName("user_msg")
	val userMsg: String? = null
)

