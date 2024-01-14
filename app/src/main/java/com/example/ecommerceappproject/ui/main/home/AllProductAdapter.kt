package com.example.ecommerceappproject.ui.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceappproject.data.model.Data
import com.example.ecommerceappproject.databinding.ItemProductBinding
import java.text.DecimalFormat

class AllProductAdapter(
    private val context: Context,
    private val list: ArrayList<Data>,
    private val listener: onItemClick

) : RecyclerView.Adapter<AllProductAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Data) {
            Glide.with(context).load(data.images[0]).into(binding.imgProductItem)
            binding.txtProductName.text = data.name
            binding.txtProductDescription.text = data.description
            binding.txtProductPrice.text = "${data.price.toString().trim().formatMoney()} đ"
            binding.imgProductItem.setOnClickListener {
                listener.itemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
//            val intent = Intent(context, ProductDetailActivity::class.java)
//            intent.putExtra(DATA, list[position])
//            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface onItemClick {
        fun itemClick(position: Int)
    }

    fun String.formatMoney(): String {
        val moneyFormat = DecimalFormat("###,###,###,###.###")
        return moneyFormat.format(this.toDouble()).toString().replace(",", ".")
    }
}