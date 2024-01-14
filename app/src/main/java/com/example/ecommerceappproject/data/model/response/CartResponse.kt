package com.example.ecommerceappproject.data.model.response

import com.example.ecommerceappproject.data.model.Cart


data class CartResponse(
    val success: Boolean,
    val data: List<Cart>
)
