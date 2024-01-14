package com.example.ecommerceappproject.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceappproject.data.model.response.ProductResponse
import com.example.ecommerceappproject.data.remote_api.Api
import com.example.ecommerceappproject.data.remote_api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {
    private val api: Api = ApiService.buildService(Api::class.java)
    var getProductResponse: MutableLiveData<ProductResponse> = MutableLiveData()
    fun getProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = api.getListProduct(null, null, null, null, null)
            if (result.isSuccessful) {
                getProductResponse.postValue(result.body())
            }
        }
    }
}