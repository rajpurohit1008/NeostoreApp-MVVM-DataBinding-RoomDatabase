package com.rajpurohit.neostoreapp.model

import com.google.gson.annotations.SerializedName


data class DataLogin (
    @SerializedName("id")
    var id: String? = null,

    @SerializedName("role_id")
    var roleId: String? = null,

    @SerializedName("first_name")
    var firstName: String? = null,

    @SerializedName("last_name")
    var lastName: String? = null,

    @SerializedName("email")
    var email: String? = null,

    @SerializedName("username")
    var username: String? = null,

    @SerializedName("profile_pic")
    var profilePic: Any? = null,

    @SerializedName("country_id")
    var countryId: Any? = null,

    @SerializedName("gender")
    var gender: String? = null,

    @SerializedName("phone_no")
    var phoneNo: String? = null,

    @SerializedName("dob")
    var dob: Any? = null,

    @SerializedName("is_active")
    var isActive: Boolean? = null,

    @SerializedName("created")
    var created: String? = null,

    @SerializedName("modified")
    var modified: String? = null,

    @SerializedName("access_token")
    var accessToken: String? = null)
