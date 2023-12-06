package com.food.oder.ui.component.fragment.home

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.viewModels
import com.food.oder.R
import com.food.oder.app.AppConstants.AMERICAN
import com.food.oder.app.AppConstants.CHINESE
import com.food.oder.app.AppConstants.ID_FOOD
import com.food.oder.app.AppConstants.THAI
import com.food.oder.app.AppConstants.VIETNAMESE
import com.food.oder.data.liveData.StateData
import com.food.oder.databinding.FragmentHomeBinding
import com.food.oder.ui.adapter.AdvertisementAdapter
import com.food.oder.ui.adapter.FoodAmericarAdapter
import com.food.oder.ui.adapter.FoodChineseAdapter
import com.food.oder.ui.adapter.FoodThaiAdapter
import com.food.oder.ui.adapter.FoodVietNamAdapter
import com.food.oder.ui.bases.BaseFragment
import com.food.oder.ui.component.activity.details.DetailsActivity
import com.food.oder.ui.component.activity.search.SearchActivity
import com.food.oder.ui.component.fragment.home.viewModel.HomeViewModel
import com.food.oder.utils.tap
import dagger.hilt.android.AndroidEntryPoint
import java.util.Timer
import java.util.TimerTask

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var listImageRanDomFood = arrayListOf<String>()
    private var adapterAd: AdvertisementAdapter? = null
    private var foodAmericarAdapter: FoodAmericarAdapter? = null
    private var foodChineseAdapter: FoodChineseAdapter? = null
    private var foodThaiAdapter: FoodThaiAdapter? = null
    private var foodVietNamAdapter: FoodVietNamAdapter? = null

    var timer: Timer? = null

    override fun getLayoutFragment() = R.layout.fragment_home

    override fun initViews() {
        super.initViews()

        homeViewModel.getRandomFood1()
        homeViewModel.getFoodCategoryByCountryAmerican(AMERICAN)
        homeViewModel.getFoodCategoryByCountryChinese(CHINESE)
        homeViewModel.getFoodCategoryByCountryThai(THAI)
        homeViewModel.getFoodCategoryByCountryVietNam(VIETNAMESE)

        adapterAd = AdvertisementAdapter()
        foodAmericarAdapter = FoodAmericarAdapter()
        foodChineseAdapter = FoodChineseAdapter()
        foodThaiAdapter = FoodThaiAdapter()
        foodVietNamAdapter = FoodVietNamAdapter()

        mBinding.viewpager2.adapter = adapterAd
        mBinding.rcvFoodAmerican.adapter = foodAmericarAdapter
        mBinding.rcvFoodChina.adapter = foodChineseAdapter
        mBinding.rcvFoodThai.adapter = foodThaiAdapter
        mBinding.rcvFoodVietnam.adapter = foodVietNamAdapter
    }

    override fun onResume() {
        super.onResume()
        startAutoSlide()
    }

    override fun onPause() {
        super.onPause()
        timer?.cancel()
        timer = null
    }

    private fun startAutoSlide() {
        // init Timer
        if (timer == null) {
            timer = Timer()
            timer!!.schedule(object : TimerTask() {
                override fun run() {
                    
                    Handler(Looper.getMainLooper()).post {
                        var currencyAd: Int = mBinding.viewpager2.currentItem
                        val positionAd: Int = listImageRanDomFood.size - 1
                        if (currencyAd < positionAd) {
                            currencyAd++
                            mBinding.viewpager2.currentItem = currencyAd
                        } else {
                            mBinding.viewpager2.currentItem = 0
                        }
                    }
                }
            }, 1000, 3000)
        }
    }

    override fun observerData() {
        super.observerData()

        homeViewModel.foodRandom1.observe(viewLifecycleOwner) {
            when (it.getStatus()) {
                StateData.DataStatus.LOADING -> {
                }

                StateData.DataStatus.SUCCESS -> {
                    it.getData().let { food ->
                        if (food != null)
                            listImageRanDomFood.add(food.meals?.get(0)?.strMealThumb.toString())
                        Log.e("TAG", "observerData1: ${listImageRanDomFood.size}")
                    }
                }

                StateData.DataStatus.ERROR -> {
                }

                else -> {
                }
            }
        }

        homeViewModel.foodRandom2.observe(viewLifecycleOwner) {
            when (it.getStatus()) {
                StateData.DataStatus.LOADING -> {
                }

                StateData.DataStatus.SUCCESS -> {
                    it.getData().let { food ->
                        if (food != null)
                            listImageRanDomFood.add(food.meals?.get(0)?.strMealThumb.toString())
                        Log.e("TAG", "observerData2: ${listImageRanDomFood.size}")
                    }
                }

                StateData.DataStatus.ERROR -> {
                }

                else -> {
                }
            }
        }

        homeViewModel.foodRandom3.observe(viewLifecycleOwner) {
            when (it.getStatus()) {
                StateData.DataStatus.LOADING -> {
                }

                StateData.DataStatus.SUCCESS -> {
                    it.getData().let { food ->
                        if (food != null) {
                            listImageRanDomFood.add(food.meals?.get(0)?.strMealThumb.toString())
                            Log.e("TAG", "observerData3: ${listImageRanDomFood.size}")
                            adapterAd?.submitData(listImageRanDomFood)
                            Log.e("TAG", "observerData3: ${adapterAd?.itemCount}")
                        }
                    }
                }

                StateData.DataStatus.ERROR -> {
                }

                else -> {
                }
            }
        }

        homeViewModel.foodAmerican.observe(viewLifecycleOwner) {
            when (it.getStatus()) {
                StateData.DataStatus.LOADING -> {
                }

                StateData.DataStatus.SUCCESS -> {
                    it.getData().let { food ->
                        if (food != null) {
                            foodAmericarAdapter?.submitData(food.meals)
                        }
                    }
                }

                StateData.DataStatus.ERROR -> {
                }

                else -> {
                }
            }
        }

        homeViewModel.foodChinese.observe(viewLifecycleOwner) {
            when (it.getStatus()) {
                StateData.DataStatus.LOADING -> {
                }

                StateData.DataStatus.SUCCESS -> {
                    it.getData().let { food ->
                        if (food != null) {
                            foodChineseAdapter?.submitData(food.meals)
                        }
                    }
                }

                StateData.DataStatus.ERROR -> {
                }

                else -> {
                }
            }
        }

        homeViewModel.foodThai.observe(viewLifecycleOwner) {
            when (it.getStatus()) {
                StateData.DataStatus.LOADING -> {
                }

                StateData.DataStatus.SUCCESS -> {
                    it.getData().let { food ->
                        if (food != null) {
                            foodThaiAdapter?.submitData(food.meals)
                        }
                    }
                }

                StateData.DataStatus.ERROR -> {
                }

                else -> {
                }
            }
        }

        homeViewModel.foodVietNam.observe(viewLifecycleOwner) {
            when (it.getStatus()) {
                StateData.DataStatus.LOADING -> {
                }

                StateData.DataStatus.SUCCESS -> {
                    it.getData().let { food ->
                        if (food != null) {
                            foodVietNamAdapter?.submitData(food.meals)
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

    override fun onClickViews() {
        super.onClickViews()

        mBinding.layoutSearch.tap {
            startActivity(Intent(requireActivity(), SearchActivity::class.java))
        }

        foodAmericarAdapter?.onClickItem = {
            startActivity(
                Intent(requireActivity(), DetailsActivity::class.java).putExtra(
                    ID_FOOD,
                    it
                )
            )
        }

        foodChineseAdapter?.onClickItem = {
            startActivity(
                Intent(requireActivity(), DetailsActivity::class.java).putExtra(
                    ID_FOOD,
                    it
                )
            )
        }

        foodThaiAdapter?.onClickItem = {
            startActivity(
                Intent(requireActivity(), DetailsActivity::class.java).putExtra(
                    ID_FOOD,
                    it
                )
            )
        }

        foodVietNamAdapter?.onClickItem = {
            startActivity(
                Intent(requireActivity(), DetailsActivity::class.java).putExtra(
                    ID_FOOD,
                    it
                )
            )
        }
    }
}
