package com.food.oder.ui.adapter

import androidx.databinding.ViewDataBinding
import com.food.oder.R
import com.food.oder.data.model.FoodCategory
import com.food.oder.data.model.MealCategory
import com.food.oder.data.model.MealRanDom
import com.food.oder.databinding.ItemAdSlideBinding
import com.food.oder.databinding.ItemFoodPopularBinding
import com.food.oder.ui.bases.BaseRecyclerView
import com.food.oder.utils.convertPrice
import com.food.oder.utils.loadImage
import com.food.oder.utils.tap

class FoodSearchAdapter(
) : BaseRecyclerView<MealRanDom>() {

    var onClickItem: ((id: String) -> Unit)? = null


    override fun getItemLayout() = R.layout.item_food_popular

    override fun submitData(newData: List<MealRanDom>) {
        list.clear()
        list.addAll(newData)
        notifyDataSetChanged()
    }

    override fun setData(binding: ViewDataBinding, item: MealRanDom, layoutPosition: Int) {
        if (binding is ItemFoodPopularBinding) {
            loadImage(binding.imageFood, item.strMealThumb)
            binding.tvTitle.text = item.strMeal
            binding.tvPrice.text = "${convertPrice(item.idMeal.toString())} €"
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onClickViews(binding: ViewDataBinding, obj: MealRanDom, layoutPosition: Int) {
        super.onClickViews(binding, obj, layoutPosition)
        binding.root.tap {
            obj.idMeal?.let { it1 -> onClickItem?.invoke(it1) }
        }
    }
}