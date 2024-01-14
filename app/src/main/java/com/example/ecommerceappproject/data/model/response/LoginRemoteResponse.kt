package com.example.ecommerceappproject.data.model.response

import com.example.ecommerceappproject.data.model.User
import com.google.gson.annotations.SerializedName

data class LoginRemoteResponse(
    val data: LoginResponse,
    val success: Boolean
)
data class LoginResponse(
    @SerializedName("user")
    val user: User,
    @SerializedName("access_token")
    val access_token: String,
)