package com.food.oder.ui.component.activity.search

import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.food.oder.R
import com.food.oder.data.liveData.StateData
import com.food.oder.databinding.ActivitySearchBinding
import com.food.oder.ui.adapter.FoodSearchAdapter
import com.food.oder.ui.bases.BaseActivity
import com.food.oder.ui.component.activity.search.viewModel.SearchViewModel
import com.food.oder.utils.tap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : BaseActivity<ActivitySearchBinding>() {

    private val searchViewModel: SearchViewModel by viewModels()

    private var foodSearchAdapter: FoodSearchAdapter? = null

    override fun getLayoutActivity() = R.layout.activity_search

    override fun initViews() {
        super.initViews()

        foodSearchAdapter = FoodSearchAdapter()

        mBinding.rcvSearch.adapter = foodSearchAdapter
    }

    override fun onClickViews() {
        super.onClickViews()

        mBinding.imgSearch.tap {
            if (mBinding.edtSearchName.text.isNotEmpty()) {
                searchViewModel.searchFood(mBinding.edtSearchName.text.toString())
            } else {
                Toast.makeText(this, "Please enter the name of the dish to search for", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun observerData() {
        super.observerData()

        searchViewModel.listFoodSearch.observe(this) {
            when (it.getStatus()) {
                StateData.DataStatus.LOADING -> {
                    mBinding.progress.visibility = View.VISIBLE
                }

                StateData.DataStatus.SUCCESS -> {
                    it.getData().let { food ->
                        if (food != null) {
                            mBinding.progress.visibility = View.GONE

                            if (food.meals?.isNotEmpty() == true) {
                                mBinding.tvNoItem.visibility = View.GONE
                                mBinding.rcvSearch.visibility = View.VISIBLE
                                food.meals?.let { it1 -> foodSearchAdapter?.submitData(it1) }
                            } else {
                                mBinding.rcvSearch.visibility = View.GONE
                                mBinding.tvNoItem.visibility = View.VISIBLE
                            }
                        }else{
                            mBinding.rcvSearch.visibility = View.GONE
                            mBinding.tvNoItem.visibility = View.VISIBLE
                        }
                    }
                }

                StateData.DataStatus.ERROR -> {
                    mBinding.progress.visibility = View.GONE
                }

                else -> {
                    mBinding.progress.visibility = View.GONE
                }
            }
        }
    }
}
