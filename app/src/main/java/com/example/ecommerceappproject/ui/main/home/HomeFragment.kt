package com.example.ecommerceappproject.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceappproject.data.model.Data
import com.example.ecommerceappproject.databinding.FragmentHomeBinding
import com.example.ecommerceappproject.ui.cart.CartActivity
import com.example.ecommerceappproject.ui.product_detail.ProductDetailActivity


class HomeFragment : Fragment() ,AllProductAdapter.onItemClick{
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private var allProductList: ArrayList<Data> = arrayListOf()
    private lateinit var allProductAdapter: AllProductAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this@HomeFragment)[HomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observerLiveData()
    }

    private fun initView() {
        viewModel.getProduct()
        binding.apply {
            //all product
            allProductAdapter =
                AllProductAdapter(requireContext(), allProductList, this@HomeFragment)
            listOfProducts.apply {
                layoutManager =
                    LinearLayoutManager(requireContext())
                adapter = allProductAdapter
            }
        }
    }

    private fun initListener() {
        binding.apply {
            imgCart.setOnClickListener {
                startActivity(Intent(requireActivity(), CartActivity::class.java))
            }
        }
    }

    private fun observerLiveData() {
        viewModel.apply {
            getProductResponse.observe(viewLifecycleOwner) {
                allProductList.clear()
                allProductList.addAll(it.data)
                allProductAdapter.notifyDataSetChanged()
            }

        }
    }

    override fun itemClick(position: Int) {
        Log.d("CheckHere", allProductList[position].id.toString())
        val intent = Intent(context, ProductDetailActivity::class.java)
        intent.putExtra("DATA", allProductList[position].id.toString())
        startActivity(intent)
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}