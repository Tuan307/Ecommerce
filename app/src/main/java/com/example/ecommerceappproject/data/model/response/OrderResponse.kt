package com.example.ecommerceappproject.data.model.response

import com.google.gson.annotations.SerializedName


data class OrderResponse(
    @SerializedName("data")
    val data: List<Datum>,
    @SerializedName("paginage")
    val paginage: Paginage
)
data class Paginage (
    val page: Long,
    val totalPage: Long
)
data class ListProduct (
    val product: Product,
    val quantity: Long
)

data class Datum (
    val id: Long,
    val status: String,
    val nameReceiver: String,
    val addressReceiver: String,
    val phoneReceiver: String,
    val type: Int,
    @SerializedName("userId")
    val userId: Long,
    val listProduct: List<ListProduct>
)
data class Product (
    val id: Long,
    val name: String,
    val price: Long,
    val description: String,
    val quantity: Long,
    val properties: String,
    val images: List<String>,
    val isSelected: Boolean,
    val isDelete: Boolean
)

