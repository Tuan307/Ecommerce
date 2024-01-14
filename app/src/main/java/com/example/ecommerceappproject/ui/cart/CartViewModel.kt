package com.example.ecommerceappproject.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceappproject.data.model.request.UpdateCartRequest
import com.example.ecommerceappproject.data.model.response.CartResponse
import com.example.ecommerceappproject.data.remote_api.Api
import com.example.ecommerceappproject.data.remote_api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel() : ViewModel() {
    private val api: Api = ApiService.buildService(Api::class.java)
    private var _cartList = MutableLiveData<CartResponse>()
    val cartList: LiveData<CartResponse>
        get() = _cartList

    var getListProductResponse: MutableLiveData<CartResponse> = MutableLiveData()

    fun getAllProductFromCart() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = api.getAllProductFromCart()
            if (result.isSuccessful) {
                getListProductResponse.postValue(result.body())
            }
        }
    }

    fun updateCart(update: UpdateCartRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = api.updateCart(update)
            if (result.isSuccessful) {
                getAllProductFromCart()
            }
        }
    }
}