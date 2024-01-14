package com.example.ecommerceappproject.data.model.response

import com.example.ecommerceappproject.data.model.Data
import com.example.ecommerceappproject.data.model.Paginate
import com.google.gson.annotations.SerializedName

data class ProductResponse(
    val data: List<Data>,
    val paginage: Paginate
)

data class DetailProductResponse(
    val success: Boolean,
    val data: DetailProduct
)

data class DetailProduct(
    val id: Long,
    val name: String,
    val price: Long,
    val description: String,
    val quantity: Long,
    val properties: String,
    val images: List<String>,
    val isSelected: Boolean,
    val isDelete: Boolean,
    @SerializedName("catalogIds")
    val catalogIds: List<Long>
)




