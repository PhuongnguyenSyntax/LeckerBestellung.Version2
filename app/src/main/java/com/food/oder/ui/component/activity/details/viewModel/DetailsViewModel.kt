package com.food.oder.ui.component.activity.details.viewModel

import com.food.oder.data.flow.collectAsSateLiveData
import com.food.oder.data.liveData.MutableStateLiveData
import com.food.oder.data.model.Cart
import com.food.oder.data.model.RanDomFood
import com.food.oder.data.repository.CartRepository
import com.food.oder.data.repository.FoodRepository
import com.food.oder.ui.bases.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val foodRepository: FoodRepository,
    private val cartRepository: CartRepository
) : BaseViewModel() {

    val foodDetails = MutableStateLiveData<RanDomFood>()
    val dataWithPrice = MutableStateLiveData<Cart>()

    fun getDetailsFood(id: String) {
        foodDetails.postLoading()
        bgScope.launch {
            foodRepository.getFoodDetails(id).collectAsSateLiveData(foodDetails)
        }
    }

    fun insertOrUpdateData(cart : Cart){
        bgScope.launch {
            cartRepository.getInsertOrUpdateData(cart)
        }
    }

    fun getDataWithPrice(price : String){
        dataWithPrice.postLoading()
        bgScope.launch {
            cartRepository.getDataWithPrice(price)
        }
    }
}
