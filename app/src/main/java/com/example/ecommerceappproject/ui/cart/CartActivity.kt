package com.example.ecommerceappproject.ui.cart

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceappproject.R
import com.example.ecommerceappproject.data.model.Cart
import com.example.ecommerceappproject.data.model.request.AddToCartRequest
import com.example.ecommerceappproject.data.model.request.UpdateCartRequest
import com.example.ecommerceappproject.databinding.ActivityCartBinding
import com.example.ecommerceappproject.ui.address.AddressActivity

class CartActivity : AppCompatActivity(), CartAdapter.OnCalculatePayment {
    private lateinit var viewModel: CartViewModel
    private lateinit var binding: ActivityCartBinding
    private var listProduct = ArrayList<Cart>()
    private lateinit var adapter: CartAdapter
    private var totalPrice: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[CartViewModel::class.java]
        viewModel.getAllProductFromCart()
        with(binding) {
            toolbar.setNavigationOnClickListener {
                finish()
            }

            btnPay.setOnClickListener {
                val intent = Intent(this@CartActivity, AddressActivity::class.java)
                val bundle = Bundle()
                bundle.putParcelableArrayList("myCart", listProduct)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
        observerLiveData()
    }

    private fun observerLiveData() {
        viewModel.getListProductResponse.observe(this@CartActivity) {
            listProduct.clear()
            listProduct.addAll(it.data.filter { quan ->
                quan.quantity > 0
            })
            totalPrice = 0
            for (i in 0 until listProduct.size) {
                totalPrice += listProduct[i].quantity * listProduct[i].product.price
            }
            binding.textTotal.text = "$totalPrice"
            adapter = CartAdapter(listProduct, this@CartActivity, this@CartActivity)
            binding.rvListCart.adapter = adapter
        }
    }

    override fun calculateAdd(count: Long, position: Int) {
        val list = ArrayList<AddToCartRequest>()
        listProduct[position].quantity = count
        for (i in 0 until listProduct.size) {
            list.add(AddToCartRequest(listProduct[i].product.id, listProduct[i].quantity))
        }
        val update = UpdateCartRequest(list)
        viewModel.updateCart(update)
    }

    override fun calculateMinus(count: Long, position: Int) {
        val list = ArrayList<AddToCartRequest>()
        if (count > 0) {
            listProduct[position].quantity = count
            for (i in 0 until listProduct.size) {
                list.add(AddToCartRequest(listProduct[i].product.id, listProduct[i].quantity))
            }
            val update = UpdateCartRequest(list)
            viewModel.updateCart(update)
        } else {
            listProduct.removeAt(position)
            adapter.notifyDataSetChanged()
            for (i in 0 until listProduct.size) {
                list.add(AddToCartRequest(listProduct[i].product.id, listProduct[i].quantity))
            }
            val update = UpdateCartRequest(list)
            viewModel.updateCart(update)
        }
    }
}