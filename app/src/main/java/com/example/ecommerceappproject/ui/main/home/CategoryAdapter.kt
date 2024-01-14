package com.example.ecommerceappproject.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceappproject.R
import com.example.ecommerceappproject.data.model.response.CategoryModel
import com.example.ecommerceappproject.databinding.LayoutItemCategoryBinding

class CategoryAdapter(
    private val list: List<CategoryModel>,
    private val listener: (Int, Boolean) -> Unit
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: LayoutItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CategoryModel) = with(binding) {
            textCategory.text = data.name.toString()
            if (data.hasChosen == true) {
                cardCategoryItem.setBackgroundColor(
                    ContextCompat.getColor(
                        root.context,
                        R.color.color_blue
                    )
                )
            } else {
                cardCategoryItem.setBackgroundColor(
                    ContextCompat.getColor(
                        root.context,
                        R.color.colorGrayDark
                    )
                )
            }
            root.setOnClickListener {
                if (data.hasChosen == true) {
                    listener(adapterPosition, false)
                } else {
                    listener(adapterPosition, true)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}