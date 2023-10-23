package com.food.oder.ui.adapter

import androidx.databinding.ViewDataBinding
import com.food.oder.R
import com.food.oder.data.model.Cart
import com.food.oder.databinding.ItemCartBinding
import com.food.oder.ui.bases.BaseRecyclerView
import com.food.oder.utils.convertPrice
import com.food.oder.utils.loadImage
import com.food.oder.utils.tap

class CartAdapter(
) : BaseRecyclerView<Cart>() {

    var deleteItem: ((id: Int) -> Unit)? = null
    var updateAmount: ((car: Cart) -> Unit)? = null
    override fun getItemLayout() = R.layout.item_cart

    override fun submitData(newData: List<Cart>) {
        list.clear()
        list.addAll(newData)
        notifyDataSetChanged()
    }

    override fun setData(binding: ViewDataBinding, item: Cart, layoutPosition: Int) {
        if (binding is ItemCartBinding) {
            loadImage(binding.imgFoodCart, item.urlImage)
            binding.tvFoodNameCart.text = item.name
            binding.tvFoodPriceCart.text = "${convertPrice(item.price)} $"
            binding.tvCount.text = item.amount.toString()

            binding.tvDelete.tap {
                deleteItem?.invoke(item.id)
            }

            binding.tvSubtract.tap {
                if (item.amount > 1) {
                    item.amount--
                    updateAmount?.invoke(
                        Cart(
                            item.id,
                            item.urlImage,
                            item.name,
                            item.price,
                            item.amount--
                        )
                    )
                    notifyItemChanged(layoutPosition)
                }
            }

            binding.tvAdd.tap {
                item.amount++
                updateAmount?.invoke(
                    Cart(
                        item.id,
                        item.urlImage,
                        item.name,
                        item.price,
                        item.amount++
                    )
                )
                notifyItemChanged(layoutPosition)
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}