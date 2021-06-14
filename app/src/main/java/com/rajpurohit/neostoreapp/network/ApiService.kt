package com.rajpurohit.neostoreapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    val BASE_URL = "http://staging.php-dev.in:8844/trainingapp/api/users/"
    val PRODUCT_URL = "http://staging.php-dev.in:8844/trainingapp/api/products/"
    val MYCART_URL = "http://staging.php-dev.in:8844/trainingapp/api/"


    fun getTableProductList(): ApiInterface {
        val tableProductRetrofit: Retrofit = Retrofit.Builder()
            .baseUrl(PRODUCT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return tableProductRetrofit.create(ApiInterface::class.java)
    }

    fun registerUserInfo(): ApiInterface {
        val registerUserRetrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return registerUserRetrofit.create(ApiInterface::class.java)
    }

    fun myCartCURD(): ApiInterface {
        val MyCartRetrofit: Retrofit = Retrofit.Builder()
            .baseUrl(MYCART_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return MyCartRetrofit.create(ApiInterface::class.java)
    }
}