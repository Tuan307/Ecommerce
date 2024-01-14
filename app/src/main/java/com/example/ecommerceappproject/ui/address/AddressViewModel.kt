package com.example.ecommerceappproject.ui.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceappproject.data.model.request.PayCashRequest
import com.example.ecommerceappproject.data.model.request.UpdateCartRequest
import com.example.ecommerceappproject.data.model.response.RegisterMessageResponse
import com.example.ecommerceappproject.data.remote_api.Api
import com.example.ecommerceappproject.data.remote_api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddressViewModel : ViewModel() {
    private val api: Api = ApiService.buildService(Api::class.java)
    private var _checkPayCash: MutableLiveData<RegisterMessageResponse> =
        MutableLiveData()
    val checkPayCash: LiveData<RegisterMessageResponse>
        get() = _checkPayCash

    fun payCash(payCashRequest: PayCashRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = api.payCash(payCashRequest)
            if (result.isSuccessful) {
                _checkPayCash.postValue(result.body())
            }
        }
    }

    fun clearCart(updateCartRequest: UpdateCartRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = api.updateCart(updateCartRequest)
            if (result.isSuccessful) {
                _checkPayCash.postValue(result.body())
            }
        }
    }
}