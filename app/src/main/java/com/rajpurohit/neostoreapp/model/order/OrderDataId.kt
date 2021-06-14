package com.rajpurohit.neostoreapp.model.order

data class OrderDataId(
	val data: List<DataItemId?>? = null,
	val status: Int? = null
)

data class DataItemId(
	val cost: Double? = null,
	val created: String? = null,
	val id: Int? = null
)

