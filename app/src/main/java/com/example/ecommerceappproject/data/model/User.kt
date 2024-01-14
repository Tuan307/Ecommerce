package com.example.ecommerceappproject.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    var id: Long? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("fullname")
    var fullname: String? = null,
    @SerializedName("username")
    var username: String? = null,
    @SerializedName("address")
    var address: String? = null,
    @SerializedName("dob")
    var dob: String? = null,
    @SerializedName("phoneNumber")
    var phoneNumber: String? = null,
    @SerializedName("role")
    var role: Long? = null
)