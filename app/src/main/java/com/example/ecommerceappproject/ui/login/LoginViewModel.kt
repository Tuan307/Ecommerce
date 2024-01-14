package com.example.ecommerceappproject.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceappproject.data.model.request.LoginRequest
import com.example.ecommerceappproject.data.model.response.LoginResponse
import com.example.ecommerceappproject.data.remote_api.Api
import com.example.ecommerceappproject.data.remote_api.ApiService
import com.example.ecommerceappproject.data.remote_api.ApiService.mToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val api: Api = ApiService.buildService(Api::class.java)
    private var loginResponse = MutableLiveData<LoginResponse>()
    val getLoginResponse = loginResponse as LiveData<LoginResponse>

    fun login(loginRequest: LoginRequest, isSaveLogin: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = api.login(loginRequest)
//            if (result.isSuccessful) {
            mToken = result.body()?.data?.access_token ?: ""
            loginResponse.postValue(result.body()?.data)
            //}
        }
    }

//    fun autoLogin(context: Context) {
//        showLoading(true)
//        parentJob = viewModelScope.launch(Dispatchers.IO) {
//            val activity = context as Activity
//            if (dataManager.getString("accessToken") != null) {
//                val token = dataManager.getString("accessToken")
//
//                NetworkModule.mToken = token
//                val intent = Intent(context, MainActivity::class.java)
//                context.startActivity(intent)
//                activity.finish()
//            } else {
//                val intent = Intent(context, LoginActivity::class.java)
//                context.startActivity(intent)
//                activity.finish()
//            }
//        }
//        registerJobFinish()
//    }
}