package com.rajpurohit.neostoreapp.model.order

data class OrderDetails(val id:Int?,
                        val order_id:Int?,
                        val quantity:Int?,
                        val total:Double?,
                        val prod_name:String?,
                        val prod_cat_name:String?,
                        val prod_image:String?
                        )
