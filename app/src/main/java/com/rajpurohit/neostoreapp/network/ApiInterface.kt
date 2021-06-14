package com.rajpurohit.neostoreapp.network

import com.rajpurohit.neostoreapp.model.*
import com.rajpurohit.neostoreapp.model.fetchaccount.FetchDetailModel
import com.rajpurohit.neostoreapp.model.mycartmodel.FetchMycart
import com.rajpurohit.neostoreapp.model.mycartmodel.Mycartcurd
import com.rajpurohit.neostoreapp.model.order.MyOrderList
import com.rajpurohit.neostoreapp.model.order.OrderDataId
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @FormUrlEncoded
    @POST("login")
    fun loginUserData(
            @Field("email") email: String?,
            @Field("password") password: String?,
    ):Call<LoginModel>

    @FormUrlEncoded
    @POST("register")
    fun registerUserData(
            @Field("first_name") first_name: String?,
            @Field("last_name") last_name: String?,
            @Field("email") email: String?,
            @Field("password") password: String?,
            @Field("confirm_password") confirm_password: String?,
            @Field("gender") gender: String?,
            @Field("phone_no") phone_no: String?,
    ): Call<LoginModel?>?

    @FormUrlEncoded
    @POST("forgot")
    fun forgotPassword(@Field("email") email: String?):Call<LoginModel>

    @GET("getUserData")
    fun getfetchAccountDetail(@Header("access_token") tokenAccess:String):Call<FetchDetailModel>


    @FormUrlEncoded
    @POST("update")
    fun editProfile(@Header("access_token")tokenAccess:String?,
        @Field("first_name") first_name: String?,
        @Field("last_name") last_name: String?,
        @Field("email") email: String?,
        @Field("dob") dob: String?,
        @Field("profile_pic") profilepic: String?,
        @Field("phone_no") phone_no: String?,
    ): Call<LoginModel?>?

    @FormUrlEncoded
    @POST("change")
    fun changePassword(@Header("access_token")accessToken:String?,
                       @Field("old_password") oldpw: String?,
                       @Field("password") pw: String?,
                       @Field("confirm_password") newpw: String?):Call<LoginModel>


    @GET("getList")
    fun getTableProduct(
            @Query("product_category_id") categoryid: String,
            @Query("limit") limt: Int,
            @Query("page") pagez: Int,
    ): Call<TableModel>


    @GET("getDetail")
    fun getProductDetail(@Query("product_id") product_id: String?
                         ): Call<ProductModel>
    @FormUrlEncoded
    @POST("setRating")
    fun addRatingbar(@Field("product_id") productId:Int?,
                     @Field("rating")rating:Int?):Call<LoginModel?>

    @FormUrlEncoded
    @POST("addToCart")
    fun addToMycart(@Header("access_token") access: String?,
                     @Field("product_id")productId :Int?,
                    @Field("quantity")quantity:Int?):Call<Mycartcurd?>?

    @GET("cart")
    fun getMycartList(@Header("access_token")access:String?) :Call<FetchMycart?>

    @FormUrlEncoded
    @POST("deleteCart")
    fun deleteMycart(@Header("access_token")access:String?,
                     @Field("product_id")productId: Int?):Call<Mycartcurd>

    @FormUrlEncoded
    @POST("editCart")
    fun editMycart(@Header("access_token")access:String?,
                     @Field("product_id")productId: Int?,
                   @Field("quantity")quantity: Int?):Call<Mycartcurd>

    @FormUrlEncoded
    @POST("order")
    fun postMyOrder(@Header("access_token")access:String?,
                     @Field("address")address: String?):Call<Mycartcurd>

     @GET("orderList")
    fun getMyOrderList(@Header("access_token")access:String?):Call<OrderDataId>

    @GET("orderDetail")
    fun getOrderIdList(@Header("access_token")access:String?,
                       @Query("order_id")orderId :Int?):Call<MyOrderList>


}