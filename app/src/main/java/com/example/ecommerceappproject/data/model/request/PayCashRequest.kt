package com.example.ecommerceappproject.data.model.request

data class PayCashRequest(
    val nameReceiver: String,
    val addressReceiver: String,
    val phoneReceiver: String,
    val type: Int,
    val productInfos: List<ProductInfo>
)

data class ProductInfo(
    val id: Long,
    val quantity: Long
)
