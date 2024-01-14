package com.example.ecommerceappproject.ui.main.order

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceappproject.R
import com.example.ecommerceappproject.data.model.response.Datum
import com.example.ecommerceappproject.databinding.LayoutItemMyOrderBinding

class MyOrderAdapter(
    private val context: Context, private val list: ArrayList<Datum>,
) :
    RecyclerView.Adapter<MyOrderAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: LayoutItemMyOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Datum) {
            with(binding) {
                txtSold.text = data.phoneReceiver //sdt
                txtLeftOver.text = data.addressReceiver
                txtProductTitle.text = data.listProduct[0].product.name
                txtAdminProductPrice.text = data.listProduct[0].product.price.toString()
                textAdminNameTitle.text = data.nameReceiver
                textType.text = if (data.type == 0) {
                    "Đơn online"
                } else {
                    "Nhận tại cửa hàng"
                }
                Glide.with(context).load(data.listProduct[0].product.images[0]).into(imgProduct)
                textCountTitle.text = data.listProduct[0].quantity.toString()
                if (data.status == "shipped") {
                    textStatusTitle.text = "Đã ship đơn"
                    textStatusTitle.setTextColor(context.resources.getColor(R.color.colorAccent))
                } else {
                    textStatusTitle.setTextColor(context.resources.getColor(R.color.color_yellow));
                    textStatusTitle.text = "Đã đặt hàng"
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutItemMyOrderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}