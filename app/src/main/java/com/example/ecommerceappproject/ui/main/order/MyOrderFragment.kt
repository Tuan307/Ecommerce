package com.example.ecommerceappproject.ui.main.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceappproject.data.model.response.Datum
import com.example.ecommerceappproject.databinding.FragmentMyOrderFragmentBinding


class MyOrderFragment : Fragment() {
    private lateinit var viewModel: MyOrderViewModel
    private lateinit var binding: FragmentMyOrderFragmentBinding
    private lateinit var adapter: MyOrderAdapter
    private var list: ArrayList<Datum> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyOrderFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this@MyOrderFragment)[MyOrderViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getWaitingOrders()
        with(binding) {
            rcvAdminOrder.layoutManager = LinearLayoutManager(requireContext())
            rcvAdminOrder.setHasFixedSize(true)
            adapter = MyOrderAdapter(requireContext(), list)
            rcvAdminOrder.adapter = adapter
        }
        observerLiveData()
    }

    private fun observerLiveData() {
        viewModel.orderResponseLiveData.observe(viewLifecycleOwner) {
            list.clear()
            list.addAll(it.data)
            adapter.notifyDataSetChanged()
        }
    }

    companion object {
        fun newInstance() = MyOrderFragment()
    }
}