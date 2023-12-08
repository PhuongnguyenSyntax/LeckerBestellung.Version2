package com.food.oder.ui.adapter

import androidx.databinding.ViewDataBinding
import com.food.oder.R
import com.food.oder.data.model.Cart
import com.food.oder.data.model.CartOrder
import com.food.oder.databinding.ItemCartBinding
import com.food.oder.databinding.ItemOrderBinding
import com.food.oder.ui.bases.BaseRecyclerView
import com.food.oder.utils.convertPrice
import com.food.oder.utils.loadImage
import com.food.oder.utils.tap

class HistoryAdapter(
) : BaseRecyclerView<CartOrder>() {

    override fun getItemLayout() = R.layout.item_order

    override fun submitData(newData: List<CartOrder>) {
        list.clear()
        list.addAll(newData)
        notifyDataSetChanged()
    }

    override fun setData(binding: ViewDataBinding, item: CartOrder, layoutPosition: Int) {
        if (binding is ItemOrderBinding) {
            binding.tvId.text = item.id.toString()
            binding.tvAddress.text = item.address
            binding.tvDate.text = item.date
            binding.tvName.text = item.fullName
            binding.tvPhone.text = item.numberPhone
            binding.tvTotalAmount.text = "${convertPrice(item.totalAmount.toString())} €"
            binding.tvMenu.text = item.menu.toString().replace("[", "").replace("]", "")
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}