package com.example.ecommerceappproject.ui.address

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceappproject.MainActivity
import com.example.ecommerceappproject.R
import com.example.ecommerceappproject.data.model.Cart
import com.example.ecommerceappproject.data.model.request.AddToCartRequest
import com.example.ecommerceappproject.data.model.request.PayCashRequest
import com.example.ecommerceappproject.data.model.request.ProductInfo
import com.example.ecommerceappproject.data.model.request.UpdateCartRequest
import com.example.ecommerceappproject.databinding.ActivityAddressBinding

class AddressActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddressBinding
    private lateinit var viewModel: AddressViewModel
    private var productList: ArrayList<Cart> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_address)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[AddressViewModel::class.java]
        val bundle = intent.extras
        productList = bundle?.getParcelableArrayList<Cart>("myCart") as ArrayList<Cart>
        initListener()
        observerLiveData()
    }

    private fun initListener() {
        binding.apply {
            toolbar.setNavigationOnClickListener {
                finish()
            }
            btnContinue.setOnClickListener {
                val ten = binding.edtName.text.toString().trim()
                val sdt = binding.edtPhone.text.toString().trim()
                val chiTiet = binding.edtChiTiet.text.toString().trim()
                val type = if (binding.home.isChecked) {
                    0
                } else {
                    1
                }
                val productInfo = productList.map { data ->
                    ProductInfo(
                        id = data.product.id,
                        quantity = data.quantity
                    )
                }
                viewModel.payCash(
                    PayCashRequest(
                        ten, chiTiet, sdt, type, productInfo
                    )
                )
            }
        }
    }

    private fun observerLiveData() {
        viewModel.checkPayCash.observe(this@AddressActivity) {
            if (it.message) {
                val updateList = arrayListOf<AddToCartRequest>()
                updateList.add(
                    AddToCartRequest(
                        0, 0
                    )
                )
                viewModel.clearCart(UpdateCartRequest(updateList))
                val intent = Intent(this@AddressActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {

            }
        }
    }
}