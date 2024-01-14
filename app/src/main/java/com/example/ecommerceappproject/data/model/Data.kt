package com.example.ecommerceappproject.data.model

import com.google.gson.annotations.SerializedName

data class Data(
    val id: Long,
    val name: String,
    val price: Long,
    val description: String,
    val quantity: Long,
    val properties: String,
    val images: List<String>,
    val isSelected: Boolean,
    val isDelete: Boolean,
    val listCatalog: List<ListCatalog>
)

data class ListCatalog(
    val id: Long,
    @SerializedName("productId")
    val productId: Long,
    @SerializedName("catalogId")
    val catalogId: Long,
    val catalog: Catalog
)

data class Catalog(
    val id: Long,
    val name: String,
    val description: String,
    val image: String
)