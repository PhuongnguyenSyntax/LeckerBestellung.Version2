package com.food.oder.ui.component.activity.details

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.food.oder.R
import com.food.oder.app.AppConstants.ID_FOOD
import com.food.oder.data.liveData.StateData
import com.food.oder.data.model.Cart
import com.food.oder.data.model.MealRanDom
import com.food.oder.databinding.ActivityDetailsBinding
import com.food.oder.ui.bases.BaseActivity
import com.food.oder.ui.component.activity.details.viewModel.DetailsViewModel
import com.food.oder.ui.component.dialog.ControlAddCartDialog
import com.food.oder.utils.convertPrice
import com.food.oder.utils.loadImage
import com.food.oder.utils.tap
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("UseCompatLoadingForDrawables")
@AndroidEntryPoint
class DetailsActivity : BaseActivity<ActivityDetailsBinding>() {

    private val detailsViewModel: DetailsViewModel by viewModels()

    private var isAddCart = false
    private var food : MealRanDom? = null

    private var urlImage = ""
    private var nameFood = ""
    private var price = ""

    override fun getLayoutActivity() = R.layout.activity_details

    override fun initViews() {
        super.initViews()

        val idFood = intent.getStringExtra(ID_FOOD)

        detailsViewModel.getDetailsFood(idFood.toString())

        mBinding.toolbar.tvTitle.text = getString(R.string.tile_details)

        if (isAddCart) {
            mBinding.btnAddToCart.background = getDrawable(R.drawable.bg_gray_shape_corner_6)
        } else {
            mBinding.btnAddToCart.background = getDrawable(R.drawable.bg_add_to_cart)
        }
    }

    override fun onClickViews() {
        super.onClickViews()

        mBinding.toolbar.imvBack.tap {
            finish()
        }

        mBinding.btnAddToCart.tap {
            if (!isAddCart) {
                mBinding.btnAddToCart.background = getDrawable(R.drawable.bg_gray_shape_corner_6)
                val dialog = food?.let { it1 -> ControlAddCartDialog(this, it1, onclickAddCart = { amount ->
                    detailsViewModel.insertOrUpdateData(
                        Cart(
                            urlImage = urlImage,
                            name = nameFood,
                            amount = amount,
                            price = price
                        )
                    )

                    Toast.makeText(this, getString(R.string.add_cart_success), Toast.LENGTH_SHORT).show()
                }, onclickCancelCart = {
                    isAddCart = false
                    mBinding.btnAddToCart.background = getDrawable(R.drawable.bg_add_to_cart)
                }) }

                dialog?.show()
            }
        }
    }

    override fun observerData() {
        super.observerData()

        detailsViewModel.foodDetails.observe(this) {

            when (it.getStatus()) {
                StateData.DataStatus.LOADING -> {
                }

                StateData.DataStatus.SUCCESS -> {
                    it.getData().let { food ->
                        if (food != null) {
                            mBinding.pressed.visibility = View.GONE
                            mBinding.btnAddToCart.visibility = View.VISIBLE


                            food.meals?.get(0)?.apply {
                               this@DetailsActivity.food = this

                                loadImage(
                                    mBinding.imvFood, this.strMealThumb
                                )
                                mBinding.tvName.text = this.strMeal
                                mBinding.tvPrice.text = "${convertPrice(this.idMeal.toString())} $"
                                mBinding.tvDetails.text = this.strInstructions

                                urlImage = this.strMealThumb.toString()
                                nameFood = this.strMeal.toString()
                                price = this.idMeal.toString()

                                detailsViewModel.getDataWithPrice(price)
                            }
                        }
                    }
                }

                StateData.DataStatus.ERROR -> {
                }

                else -> {
                }
            }
        }

        detailsViewModel.dataWithPrice.observe(this) {

            when (it.getStatus()) {
                StateData.DataStatus.LOADING -> {
                }

                StateData.DataStatus.SUCCESS -> {
                    it.getData().let { food ->
                        Log.e("TAG", "observerData: $food", )
                        isAddCart = food != null
                        if (isAddCart) {
                            mBinding.btnAddToCart.background = getDrawable(R.drawable.bg_gray_shape_corner_6)
                        } else {
                            mBinding.btnAddToCart.background = getDrawable(R.drawable.bg_add_to_cart)
                        }
                    }
                }

                StateData.DataStatus.ERROR -> {
                }

                else -> {
                }
            }
        }
    }
}
