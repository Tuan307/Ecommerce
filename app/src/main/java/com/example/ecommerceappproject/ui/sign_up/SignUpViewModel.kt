package com.example.ecommerceappproject.ui.sign_up

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceappproject.data.model.request.SignUpRequest
import com.example.ecommerceappproject.data.model.response.RegisterMessageResponse
import com.example.ecommerceappproject.data.remote_api.Api
import com.example.ecommerceappproject.data.remote_api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel() :
    ViewModel() {

    private val api: Api = ApiService.buildService(Api::class.java)
    var message: MutableLiveData<RegisterMessageResponse> = MutableLiveData()

    fun signUp(request: SignUpRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = api.signUp(request)
            if(result.isSuccessful){
                message.postValue(result.body())
            }
        }
    }
}