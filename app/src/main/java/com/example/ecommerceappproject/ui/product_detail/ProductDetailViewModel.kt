package com.example.ecommerceappproject.ui.product_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceappproject.data.model.request.AddToCartRequest
import com.example.ecommerceappproject.data.model.response.DetailProduct
import com.example.ecommerceappproject.data.remote_api.Api
import com.example.ecommerceappproject.data.remote_api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductDetailViewModel() : ViewModel() {
    private val api: Api = ApiService.buildService(Api::class.java)
    private var _checkAddToCart: MutableLiveData<Boolean> = MutableLiveData()
    val checkAddToCart: LiveData<Boolean>
        get() = _checkAddToCart

    private var _buyNowResponse: MutableLiveData<Boolean> = MutableLiveData()
    val buyNowResponse: LiveData<Boolean>
        get() = _buyNowResponse

    fun addToCart(addToCartRequest: AddToCartRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = api.addToCart(addToCartRequest)
            _checkAddToCart.postValue(result.body()?.message)
        }
    }

    fun buyNow(addToCartRequest: AddToCartRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = api.addToCart(addToCartRequest)
            _buyNowResponse.postValue(result.body()?.message)
        }
    }

    private var _productDetailResponse: MutableLiveData<DetailProduct> = MutableLiveData()
    val productDetailResponse: LiveData<DetailProduct>
        get() = _productDetailResponse

    fun getProductDetail(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = api.getDetailNewProduct(id)
            if (result.isSuccessful) {
                _productDetailResponse.postValue(result.body()?.data)
            }
        }
    }
}