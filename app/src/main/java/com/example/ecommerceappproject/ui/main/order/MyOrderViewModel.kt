package com.example.ecommerceappproject.ui.main.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceappproject.data.model.response.OrderResponse
import com.example.ecommerceappproject.data.remote_api.Api
import com.example.ecommerceappproject.data.remote_api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MyOrderViewModel() : ViewModel() {
    private val api: Api = ApiService.buildService(Api::class.java)
    private var _orderResponseLiveData = MutableLiveData<OrderResponse>()
    val orderResponseLiveData: LiveData<OrderResponse>
        get() = _orderResponseLiveData

    fun getWaitingOrders() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = api.getAllOrders()
            if (result.isSuccessful) {
                _orderResponseLiveData.postValue(result.body())
            }
        }
    }
}