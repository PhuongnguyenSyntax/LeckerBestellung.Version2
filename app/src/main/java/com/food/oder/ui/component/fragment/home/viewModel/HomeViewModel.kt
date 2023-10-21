package com.food.oder.ui.component.fragment.home.viewModel

import com.food.oder.data.flow.collectAsSateLiveData
import com.food.oder.data.liveData.MutableStateLiveData
import com.food.oder.data.model.FoodCategory
import com.food.oder.data.model.RanDomFood
import com.food.oder.data.repository.FoodRepository
import com.food.oder.ui.bases.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val foodRepository: FoodRepository
) : BaseViewModel() {
    val foodRandom1 = MutableStateLiveData<RanDomFood>()
    val foodRandom2 = MutableStateLiveData<RanDomFood>()
    val foodRandom3 = MutableStateLiveData<RanDomFood>()

    val foodAmerican = MutableStateLiveData<FoodCategory>()
    val foodChinese = MutableStateLiveData<FoodCategory>()
    val foodThai = MutableStateLiveData<FoodCategory>()
    val foodVietNam = MutableStateLiveData<FoodCategory>()

    fun getRandomFood1() {
        runBlocking {
            foodRandom1.postLoading()
            foodRepository.getRanDomFood().collectAsSateLiveData(foodRandom1)
            getRandomFood2()
        }
    }

    private fun getRandomFood2() {
        runBlocking {
            foodRandom2.postLoading()
            foodRepository.getRanDomFood().collectAsSateLiveData(foodRandom2)
            getRandomFood3()
        }
    }

    private fun getRandomFood3() {
        runBlocking {
            foodRandom3.postLoading()
            foodRepository.getRanDomFood().collectAsSateLiveData(foodRandom3)
        }
    }

    fun getFoodCategoryByCountryAmerican(country: String) {
        foodAmerican.postLoading()
        bgScope.launch {
            foodRepository.getFoodCategory(country).collectAsSateLiveData(foodAmerican)
        }
    }

    fun getFoodCategoryByCountryChinese(country: String) {
        foodChinese.postLoading()
        bgScope.launch {
            foodRepository.getFoodCategory(country).collectAsSateLiveData(foodChinese)
        }
    }

    fun getFoodCategoryByCountryThai(country: String) {
        foodThai.postLoading()
        bgScope.launch {
            foodRepository.getFoodCategory(country).collectAsSateLiveData(foodThai)
        }
    }

    fun getFoodCategoryByCountryVietNam(country: String) {
        foodVietNam.postLoading()
        bgScope.launch {
            foodRepository.getFoodCategory(country).collectAsSateLiveData(foodVietNam)
        }
    }
}