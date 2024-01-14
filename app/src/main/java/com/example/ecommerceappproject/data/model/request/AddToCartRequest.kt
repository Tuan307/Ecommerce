package com.example.ecommerceappproject.data.model.request

import com.google.gson.annotations.SerializedName

class AddToCartRequest(
    @SerializedName("id")
    var id: Long,
    @SerializedName("quantity")
    var quantity: Long,
)