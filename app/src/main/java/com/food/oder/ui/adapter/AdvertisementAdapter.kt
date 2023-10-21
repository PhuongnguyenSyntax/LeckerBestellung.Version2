package com.food.oder.ui.adapter

import androidx.databinding.ViewDataBinding
import com.food.oder.R
import com.food.oder.databinding.ItemAdSlideBinding
import com.food.oder.ui.bases.BaseRecyclerView
import com.food.oder.utils.loadImage

class AdvertisementAdapter(
) :  BaseRecyclerView<String>() {

    override fun getItemLayout() = R.layout.item_ad_slide

    override fun submitData(newData: List<String>) {
        list.clear()
        list.addAll(newData)
        notifyDataSetChanged()
    }

    override fun setData(binding: ViewDataBinding, item: String, layoutPosition: Int) {
        if (binding is ItemAdSlideBinding) {
            loadImage(binding.imgAd, item)
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}