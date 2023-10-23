package com.food.oder.ui.adapter

import androidx.databinding.ViewDataBinding
import com.food.oder.R
import com.food.oder.data.model.MealCategory
import com.food.oder.databinding.ItemAdSlideBinding
import com.food.oder.databinding.ItemFoodPopularBinding
import com.food.oder.ui.bases.BaseRecyclerView
import com.food.oder.utils.loadImage
import com.food.oder.utils.tap

class FoodChineseAdapter(
) :  BaseRecyclerView<MealCategory>() {
    var onClickItem: ((id: String) -> Unit)? = null

    override fun getItemLayout() = R.layout.item_food_popular

    override fun submitData(newData: List<MealCategory>) {
        list.clear()
        list.addAll(newData)
        notifyDataSetChanged()
    }

    override fun setData(binding: ViewDataBinding, item: MealCategory, layoutPosition: Int) {
        if (binding is ItemFoodPopularBinding) {
            loadImage(binding.imageFood, item.strMealThumb)
            binding.tvTitle.text = item.strMeal
            binding.tvTitle.text = "${item.idMeal} $"
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onClickViews(binding: ViewDataBinding, obj: MealCategory, layoutPosition: Int) {
        super.onClickViews(binding, obj, layoutPosition)

        binding.root.tap {
            onClickItem?.invoke(obj.idMeal)
        }
    }
}