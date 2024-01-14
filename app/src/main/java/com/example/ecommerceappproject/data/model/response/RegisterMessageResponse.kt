package com.example.ecommerceappproject.data.model.response

import com.squareup.moshi.Json


data class RegisterMessageResponse(
    @Json(name = "success") val message: Boolean
)
