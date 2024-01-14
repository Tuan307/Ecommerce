package com.example.ecommerceappproject.data.model.request

import com.google.gson.annotations.SerializedName

class UpdateCartRequest(
    @SerializedName("cart")
    var cart: List<AddToCartRequest>
)