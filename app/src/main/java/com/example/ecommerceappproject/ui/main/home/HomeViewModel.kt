package com.example.ecommerceappproject.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceappproject.data.model.response.CategoryModel
import com.example.ecommerceappproject.data.model.response.ProductResponse
import com.example.ecommerceappproject.data.remote_api.Api
import com.example.ecommerceappproject.data.remote_api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {
    private val api: Api = ApiService.buildService(Api::class.java)
    var getProductResponse: MutableLiveData<ProductResponse> = MutableLiveData()
    var searchProductResponse: MutableLiveData<ProductResponse> = MutableLiveData()
    var getCategoryResponse: MutableLiveData<List<CategoryModel>> = MutableLiveData()
    fun getProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = api.getListProduct(null, null, null, null, null)
            if (result.isSuccessful) {
                getProductResponse.postValue(result.body())
            }
        }
    }

    fun searchProduct(keyWord: String?, category: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = api.getListProduct(null, null, keyWord, null, category)
            if (result.isSuccessful) {
                searchProductResponse.postValue(result.body())
            }
        }
    }

    fun getCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = api.getAllCategory(null, null, null, null)
            if (result.isSuccessful) {
                getCategoryResponse.postValue(result.body()?.data.orEmpty())
            }
        }
    }
}