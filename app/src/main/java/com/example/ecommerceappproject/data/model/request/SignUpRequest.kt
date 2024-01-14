package com.example.ecommerceappproject.data.model.request

data class SignUpRequest(
    val email: String,
    val password: String,
    val fullname: String,
    val username: String,
    val address: String,
    val dob: String?,
    val phoneNumber: String
)