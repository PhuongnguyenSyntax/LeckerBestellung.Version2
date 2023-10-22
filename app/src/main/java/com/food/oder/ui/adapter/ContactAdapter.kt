package com.food.oder.ui.adapter

import androidx.databinding.ViewDataBinding
import com.food.oder.R
import com.food.oder.data.model.Contact
import com.food.oder.databinding.ItemContactBinding
import com.food.oder.ui.bases.BaseRecyclerView
import com.food.oder.utils.loadImage
import com.food.oder.utils.tap

class ContactAdapter(
) : BaseRecyclerView<Contact>() {

    var fbOnclick: (() -> Unit)? = null

    override fun getItemLayout() = R.layout.item_contact

    override fun submitData(newData: List<Contact>) {
        list.clear()
        list.addAll(newData)
        notifyDataSetChanged()
    }

    override fun setData(binding: ViewDataBinding, item: Contact, layoutPosition: Int) {
        if (binding is ItemContactBinding) {
            binding.imgContact.setBackgroundResource(item.urlImage)
            binding.tvContact.text = item.name
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onClickViews(binding: ViewDataBinding, obj: Contact, layoutPosition: Int) {
        super.onClickViews(binding, obj, layoutPosition)

        binding.root.tap {
            when(layoutPosition){
                0 -> {
                    fbOnclick?.invoke()
                }
            }
        }
    }
}