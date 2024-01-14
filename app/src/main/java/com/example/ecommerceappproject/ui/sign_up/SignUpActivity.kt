package com.example.ecommerceappproject.ui.sign_up

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceappproject.R
import com.example.ecommerceappproject.data.model.request.SignUpRequest
import com.example.ecommerceappproject.databinding.ActivitySignUpBinding
import com.example.ecommerceappproject.ui.login.LoginActivity

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        initListener()
        observerLiveData()
    }

    private fun initListener() {
        binding.btnRegister.setOnClickListener {
            val email: String = binding.email.text.toString().trim()
            val username: String = binding.inputUserName.text.toString().trim()
            val phoneNum: String = binding.editPhone.text.toString().trim()
            val password: String = binding.password.text.toString().trim()
            val name: String = binding.editTen.text.toString().trim()
            val address: String = binding.editDiaChi.text.toString().trim()
            if (phoneNum.isEmpty() || email.isEmpty() || password.isEmpty() || name.isEmpty() || address.isEmpty() || username.isEmpty()) {
                Toast.makeText(this, "Không được để trống dữ liệu", Toast.LENGTH_SHORT).show()
            } else {
                binding.pbLoad.visibility = View.VISIBLE
                viewModel.signUp(
                    SignUpRequest(
                        email,
                        password,
                        name,
                        username,
                        address,
                        null,
                        phoneNum
                    )
                )
            }
        }

        binding.ivBack.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
            finish()
        }
    }

    private fun observerLiveData() {
        viewModel.message.observe(this@SignUpActivity) {
            if (it.message) {
                binding.pbLoad.visibility = View.GONE
                Toast.makeText(this, "Đăng ký tài khoản thành công", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                binding.pbLoad.visibility = View.GONE
                Toast.makeText(this, "Đăng ký tài khoản thất bại", Toast.LENGTH_SHORT).show()
            }
        }
    }
}