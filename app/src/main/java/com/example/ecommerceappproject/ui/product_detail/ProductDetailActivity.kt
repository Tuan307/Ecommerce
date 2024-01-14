package com.example.ecommerceappproject.ui.product_detail

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceappproject.R
import com.example.ecommerceappproject.data.model.request.AddToCartRequest
import com.example.ecommerceappproject.databinding.ActivityProductDetailBinding
import com.example.ecommerceappproject.ui.cart.CartActivity

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var viewModel: ProductDetailViewModel
    private lateinit var data: String
    private lateinit var adapter: ProductImageAdapter
    private var list: List<String> = arrayListOf()
    private var numberOfProductsInCart: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[ProductDetailViewModel::class.java]
        data = intent.getStringExtra("DATA") ?: ""
        if (data != "") {
            viewModel.getProductDetail(data)
        }
        initListener()
        observerLiveData()
    }

    private fun initListener() {
        with(binding) {

            addIntoCart.setOnClickListener {
                viewModel.addToCart(AddToCartRequest(data.toLong(), 1))
            }

            toolbar.setNavigationOnClickListener {
                finish()
            }

            imgCart.setOnClickListener {
                startActivity(Intent(this@ProductDetailActivity, CartActivity::class.java))
                finish()
            }
        }
    }

    private fun observerLiveData() {
        viewModel.productDetailResponse.observe(this@ProductDetailActivity) {
            with(binding) {
                nameProduct.text = it.name
                price.text = getString(R.string.price_product, it.price)
                size.text = getString(R.string.weight, it.quantity.toString())
                describe.text = getString(R.string.describe, it.description)
                list = it.images
                adapter = ProductImageAdapter(this@ProductDetailActivity, list)
                rcvImageItem.adapter = adapter
               // numberOfProductsInCart = dataManager.getInt(NUMBER_OF_PRODUCTS_IN_CART)
                tvNumberProduct.text = numberOfProductsInCart.toString()
            }
        }
        viewModel.checkAddToCart.observe(this@ProductDetailActivity) {
            if (it) {
                Toast.makeText(
                    this@ProductDetailActivity,
                    "Thêm vào giỏ hàng thành công",
                    Toast.LENGTH_SHORT
                ).show()
                numberOfProductsInCart += 1
                binding.tvNumberProduct.text = numberOfProductsInCart.toString()
                //dataManager.save(NUMBER_OF_PRODUCTS_IN_CART, numberOfProductsInCart)
            } else {
                Toast.makeText(
                    this@ProductDetailActivity,
                    "Thêm vào giỏ hàng thất bại",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}