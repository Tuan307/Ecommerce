package com.example.ecommerceappproject.data.model.response

data class CategoryResponse(
    val success: Boolean?,
    val data: List<CategoryModel>?,
    val paginage: Paginage?
)

data class CategoryModel(
    val id: Int?,
    val name: String?,
    val description: String?,
    val image: String?,
    var hasChosen:Boolean? = false
)