package com.food.oder.ui.component.fragment.cart.viewModel

import com.food.oder.data.database.FoodDataBase
import com.food.oder.data.flow.collectAsSateLiveData
import com.food.oder.data.liveData.MutableStateLiveData
import com.food.oder.data.model.Cart
import com.food.oder.ui.bases.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(
    private val dataBase: FoodDataBase
) : BaseViewModel() {

    val listDataCart = MutableStateLiveData<List<Cart>?>()

    fun getListDataCart(){
        listDataCart.postLoading()
        bgScope.launch {
            dataBase.cartDao.getAllIData().collectAsSateLiveData(listDataCart)
        }
    }

    fun insertOrUpdateCart(cart : Cart){
        bgScope.launch {
            dataBase.cartDao.insertOrUpdateData(cart)
        }

        getListDataCart()
    }

    fun deleteCart(id : Int){
        bgScope.launch {
            dataBase.cartDao.deleteData(id)
        }

        getListDataCart()
    }

    fun deleteAllDataCart(){
        bgScope.launch {
            dataBase.cartDao.deleteAllData()
        }

        getListDataCart()
    }

}