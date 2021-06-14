package com.rajpurohit.neostoreapp.model.mycartmodel

data class FetchMycart(
        val status :Int? =null,
        val data:List<MycartData>? = null,
        val count :Int? = null,
        val total:Float? = null
)
