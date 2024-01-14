package com.example.ecommerceappproject.ui.product_detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceappproject.databinding.ProductImageItemBinding

class ProductImageAdapter(
    private val context: Context,
    private val list: List<String>
) : RecyclerView.Adapter<ProductImageAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ProductImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String) {
            Glide.with(context).load(data).into(binding.imgProductDetail)
            binding.txtImageCount.text = "${adapterPosition + 1}/${list.size}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ProductImageItemBinding.inflate(
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