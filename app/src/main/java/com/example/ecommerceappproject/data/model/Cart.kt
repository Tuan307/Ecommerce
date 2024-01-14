package com.example.ecommerceappproject.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Cart(
    val product: CartProduct,
    var quantity: Long
) : Parcelable

@Parcelize
data class CartProduct(
    val id: Long,
    val name: String,
    val price: Long,
    val description: String,
    val quantity: Long,
    val properties: String,
    val images: List<String>,
    val isSelected: Boolean,
    val isDelete: Boolean
) : Parcelable
