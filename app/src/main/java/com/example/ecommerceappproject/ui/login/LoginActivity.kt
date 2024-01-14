package com.example.ecommerceappproject.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceappproject.MainActivity
import com.example.ecommerceappproject.R
import com.example.ecommerceappproject.data.model.request.LoginRequest
import com.example.ecommerceappproject.databinding.ActivityLoginBinding
import com.example.ecommerceappproject.ui.sign_up.SignUpActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this@LoginActivity)[LoginViewModel::class.java]
        initView()
        observerLiveData()
    }

    private fun initView() = with(binding) {
        edtInputAccount.setText("admin1@gmail.com")
        edtInputPassword.setText("123123")
        btnLogin.setOnClickListener {
            viewModel.login(
                LoginRequest(
                    edtInputAccount.getText().toString(),
                    edtInputPassword.getText().toString()
                ),
                cbMemoLogin.isChecked
            )
        }
        tvRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
        }
    }

    private fun observerLiveData() {
        viewModel.getLoginResponse.observe(this@LoginActivity) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }

    }
}