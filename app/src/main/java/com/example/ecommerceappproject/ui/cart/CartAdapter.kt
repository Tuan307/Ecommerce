package com.example.ecommerceappproject.ui.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceappproject.R
import com.example.ecommerceappproject.data.model.Cart

class CartAdapter(
    private val listProduct: ArrayList<Cart>,
    val context: Context,
    private val listener: OnCalculatePayment
) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var avatar: ImageView
        var nameProduct: TextView
        var costProduct: TextView
        var minus: ImageView
        var add: ImageView
        var total: TextView
        var allrl: RelativeLayout

        init {
            avatar = view.findViewById(R.id.ivAvatar)
            nameProduct = view.findViewById(R.id.name)
            costProduct = view.findViewById(R.id.cost)
            minus = view.findViewById(R.id.minus)
            add = view.findViewById(R.id.add)
            total = view.findViewById(R.id.tvNumber)
            allrl = view.findViewById(R.id.allrl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product_cart, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = listProduct[position]
        val data = listProduct[position].product
        Glide.with(context).load(listProduct[position].product.images[0]).error(R.drawable.cover)
            .into(holder.avatar)
        holder.nameProduct.text = listProduct[position].product.name
        holder.costProduct.text = listProduct[position].product.price.toString()
        holder.total.text = listProduct[position].quantity.toString()
        holder.add.setOnClickListener {
            val text = holder.total.text.toString().toInt()
            holder.total.text = "${text + 1}"
            listener.calculateAdd((text + 1).toLong(), position)
        }
        holder.minus.setOnClickListener {
            val text = holder.total.text.toString().toInt()
            if (text > 0) {
                holder.total.text = "${text - 1}"
                listener.calculateMinus((text - 1).toLong(), position)
            }
        }
    }

    interface OnCalculatePayment {
        fun calculateAdd(count: Long, position: Int)
        fun calculateMinus(count: Long, position: Int)
    }
}