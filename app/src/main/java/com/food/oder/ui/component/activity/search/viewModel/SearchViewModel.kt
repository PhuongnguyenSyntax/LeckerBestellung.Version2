package com.food.oder.ui.component.activity.search.viewModel

import com.food.oder.data.flow.collectAsSateLiveData
import com.food.oder.data.liveData.MutableStateLiveData
import com.food.oder.data.model.RanDomFood
import com.food.oder.data.repository.FoodRepository
import com.food.oder.ui.bases.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val foodRepository: FoodRepository
) : BaseViewModel() {

    val listFoodSearch = MutableStateLiveData<RanDomFood>()

    fun searchFood(name : String){
        listFoodSearch.postLoading()
        bgScope.launch {
            foodRepository.searchFood(name).collectAsSateLiveData(listFoodSearch)
        }
    }
}