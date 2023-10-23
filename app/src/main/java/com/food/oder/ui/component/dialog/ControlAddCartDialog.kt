package com.food.oder.ui.component.dialog

import android.content.Context
import android.view.View
import com.food.oder.R
import com.food.oder.data.model.MealRanDom
import com.food.oder.data.model.RanDomFood
import com.food.oder.databinding.DialogControllAddCartBinding
import com.food.oder.databinding.DialogDeleteCartBinding
import com.food.oder.ui.bases.BaseDialog
import com.food.oder.utils.convertPrice
import com.food.oder.utils.loadImage
import com.food.oder.utils.tap
import java.util.IdentityHashMap

class ControlAddCartDialog(
    context: Context,
    val food : MealRanDom,
    val onclickAddCart: ((count : Int) -> Unit)? = null,
    val onclickCancelCart: () -> Unit
) : BaseDialog<DialogControllAddCartBinding>(context) {

    private var amount = 1

    override fun getLayoutDialog() = R.layout.dialog_controll_add_cart

    override fun initViews() {
        super.initViews()
        loadImage(
            mBinding.imgFoodCart, food.strMealThumb
        )

        mBinding.tvFoodNameCart.text = food.strMeal
        mBinding.tvFoodPriceCart.text = "${convertPrice(food.idMeal.toString())} $"

        if (amount == 1){
            mBinding.tvSubtract.visibility = View.INVISIBLE
        }
    }

    override fun onClickViews() {
        super.onClickViews()
        mBinding.tvAddCart.setOnClickListener {
            onclickAddCart?.invoke(amount)
            dismiss()
        }
        mBinding.tvCancel.setOnClickListener {
            amount = 1
            onclickCancelCart()
            dismiss()
        }

        mBinding.tvSubtract.tap {
            amount--
            if (amount == 1) {
                mBinding.tvSubtract.visibility = View.GONE
            }
            mBinding.tvCount.text = amount.toString()
        }

        mBinding.tvAdd.tap {
            amount++
            mBinding.tvCount.text = amount.toString()
            mBinding.tvSubtract.visibility = View.VISIBLE
        }
    }
}