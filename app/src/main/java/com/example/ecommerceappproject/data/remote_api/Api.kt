package com.example.ecommerceappproject.data.remote_api

import com.example.ecommerceappproject.data.model.request.AddToCartRequest
import com.example.ecommerceappproject.data.model.request.LoginRequest
import com.example.ecommerceappproject.data.model.request.PayCashRequest
import com.example.ecommerceappproject.data.model.request.SignUpRequest
import com.example.ecommerceappproject.data.model.request.UpdateCartRequest
import com.example.ecommerceappproject.data.model.response.CartResponse
import com.example.ecommerceappproject.data.model.response.DetailProductResponse
import com.example.ecommerceappproject.data.model.response.LoginRemoteResponse
import com.example.ecommerceappproject.data.model.response.Message
import com.example.ecommerceappproject.data.model.response.OrderResponse
import com.example.ecommerceappproject.data.model.response.ProductResponse
import com.example.ecommerceappproject.data.model.response.RegisterMessageResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginRemoteResponse>

    @GET("product")
    suspend fun getListProduct(
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query("keyword") keyword: String?,
        @Query("sort") sort: String?,
        @Query("catalogId") catalogId: Int?,
    ): Response<ProductResponse>

    @GET("product/{id}")
    suspend fun getDetailNewProduct(
        @Path("id") id: String?,
    ): Response<DetailProductResponse>

    @POST("product/cart")
    suspend fun addToCart(@Body addToCartRequest: AddToCartRequest): Response<RegisterMessageResponse>

    @POST("order")
    suspend fun payCash(@Body payment: PayCashRequest): Response<RegisterMessageResponse>

    @PATCH("product/cart/update")
    suspend fun updateCart(@Body addToCartRequest: UpdateCartRequest): Response<RegisterMessageResponse>

    @GET("product/cart/array")
    suspend fun getAllProductFromCart(
    ): Response<CartResponse>

    @GET("order")
    suspend fun getAllOrders(): Response<OrderResponse>

    @POST("auth/register")
    suspend fun signUp(@Body request: SignUpRequest): Response<RegisterMessageResponse>

}