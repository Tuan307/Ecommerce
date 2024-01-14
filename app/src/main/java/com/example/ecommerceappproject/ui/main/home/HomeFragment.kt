package com.example.ecommerceappproject.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceappproject.data.model.Data
import com.example.ecommerceappproject.data.model.response.CategoryModel
import com.example.ecommerceappproject.data.remote_api.ApiService
import com.example.ecommerceappproject.databinding.FragmentHomeBinding
import com.example.ecommerceappproject.ui.cart.CartActivity
import com.example.ecommerceappproject.ui.login.LoginActivity
import com.example.ecommerceappproject.ui.product_detail.ProductDetailActivity


class HomeFragment : Fragment(), AllProductAdapter.onItemClick {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private var allProductList: ArrayList<Data> = arrayListOf()
    private var allCategoryList: ArrayList<CategoryModel> = arrayListOf()
    private lateinit var allProductAdapter: AllProductAdapter
    private lateinit var categoryAdapter: CategoryAdapter
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
        viewModel.getCategory()
        with(binding) {
            imgLogout.setOnClickListener {
                ApiService.mToken = ""
                startActivity(Intent(requireActivity(), LoginActivity::class.java))
                requireActivity().finishAffinity()
            }
            //all product
            categoryAdapter = CategoryAdapter(allCategoryList, ::clickCategory)
            listOfCategory.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = categoryAdapter
            }
            allProductAdapter =
                AllProductAdapter(requireContext(), allProductList, this@HomeFragment)
            listOfProducts.apply {
                layoutManager =
                    LinearLayoutManager(requireContext())
                adapter = allProductAdapter
            }
            edtSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    val cate = allCategoryList.find {
                        it.hasChosen == true
                    }
                    viewModel.searchProduct(p0.toString(), cate?.id)
                }

                override fun afterTextChanged(p0: Editable?) {
                }
            })
        }
    }

    private fun clickCategory(i: Int, b: Boolean) {
        if (b) {
            viewModel.searchProduct(null, allCategoryList[i].id)
        } else {
            viewModel.getProduct()
        }
        val newList = arrayListOf<CategoryModel>()
        newList.addAll(allCategoryList.map { data ->
            CategoryModel(
                data.id, data.name, data.description, data.image, false
            )
        })
        allCategoryList.clear()
        allCategoryList.addAll(newList)
        if (allCategoryList[i].id != null) {
            allCategoryList[i].hasChosen = b
        }
        categoryAdapter.notifyDataSetChanged()
    }

    private fun initListener() {
        binding.apply {
            imgCart.setOnClickListener {
                startActivity(Intent(requireActivity(), CartActivity::class.java))
            }
        }
    }

    private fun observerLiveData() {
        with(viewModel) {
            getCategoryResponse.observe(viewLifecycleOwner) {
                allCategoryList.clear()
                allCategoryList.addAll(it)
                categoryAdapter.notifyDataSetChanged()
            }
            getProductResponse.observe(viewLifecycleOwner) {
                allProductList.clear()
                allProductList.addAll(it.data)
                allProductAdapter.notifyDataSetChanged()
            }
            searchProductResponse.observe(viewLifecycleOwner) {
                allProductList.clear()
                allProductList.addAll(it.data)
                allProductAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun itemClick(position: Int) {
        val intent = Intent(context, ProductDetailActivity::class.java)
        intent.putExtra("DATA", allProductList[position].id.toString())
        startActivity(intent)
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}