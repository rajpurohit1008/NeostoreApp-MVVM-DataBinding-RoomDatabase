package com.rajpurohit.neostoreapp.model.order

data class DataItem(
    val cost: Float? = null,
    val created: String? = null,
    val id: Int? = null,
    val order_details:List<OrderDetails>
)



