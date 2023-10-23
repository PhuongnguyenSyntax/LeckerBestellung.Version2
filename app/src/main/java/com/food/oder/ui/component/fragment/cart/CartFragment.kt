package com.food.oder.ui.component.fragment.cart

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.food.oder.R
import com.food.oder.app.AppConstants.LIST_NAME_DATACART
import com.food.oder.app.AppConstants.TOTAL_AMOUNT
import com.food.oder.data.liveData.StateData
import com.food.oder.data.model.Cart
import com.food.oder.databinding.FragmentCartBinding
import com.food.oder.ui.adapter.CartAdapter
import com.food.oder.ui.bases.BaseFragment
import com.food.oder.ui.component.activity.BottomSheetOrderCartActivity
import com.food.oder.ui.component.dialog.DeleteDialog
import com.food.oder.ui.component.fragment.cart.viewModel.CartViewModel
import com.food.oder.utils.convertPrice
import com.food.oder.utils.tap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>() {
    private val cartViewModel: CartViewModel by viewModels()

    private var cartAdapter: CartAdapter? = null

    private var totalAmount = 0

    private var listDataCart: List<Cart>? = null

    private var listNameDataCart = arrayListOf<String>()

    override fun getLayoutFragment() = R.layout.fragment_cart

    override fun initViews() {
        super.initViews()

        cartAdapter = CartAdapter()
        cartViewModel.getListDataCart()
        mBinding.toolbar.imvBack.visibility = View.GONE
        mBinding.toolbar.tvTitle.text = getString(R.string.cart)

        mBinding.rcvFoodCart.adapter = cartAdapter
    }

    override fun observerData() {
        super.observerData()

        cartViewModel.listDataCart.observe(this) {
            when (it.getStatus()) {
                StateData.DataStatus.LOADING -> {
                }

                StateData.DataStatus.SUCCESS -> {
                    it.getData().let { listDataCart ->
                        listNameDataCart.clear()
                        if (listDataCart != null) {

                            this.listDataCart = listDataCart

                            cartAdapter?.submitData(listDataCart)
                            if (listDataCart.isNotEmpty()) {
                                mBinding.rcvFoodCart.visibility = View.VISIBLE
                                mBinding.layoutBottom.visibility = View.VISIBLE
                                mBinding.tvNoItem.visibility = View.GONE

                                totalAmount = 0

                                for (i in 0..listDataCart.lastIndex) {
                                    listNameDataCart.add(listDataCart[i].name)
                                    totalAmount += (listDataCart[i].amount * listDataCart[i].price.toInt())
                                }

                                mBinding.tvTotalPrice.text =
                                    "${convertPrice(totalAmount.toString())} $"
                            } else {
                                mBinding.rcvFoodCart.visibility = View.GONE
                                mBinding.layoutBottom.visibility = View.GONE
                                mBinding.tvNoItem.visibility = View.VISIBLE
                            }
                        } else {
                            mBinding.rcvFoodCart.visibility = View.GONE
                            mBinding.layoutBottom.visibility = View.GONE
                            mBinding.tvNoItem.visibility = View.VISIBLE
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

        cartAdapter?.updateAmount = {
            cartViewModel.insertOrUpdateCart(it)
        }

        cartAdapter?.deleteItem = {
            val deleteDialog = DeleteDialog(requireContext()) {
                cartViewModel.deleteCart(it)
            }

            deleteDialog.show()
        }

        mBinding.tvOrderCart.tap {
            startActivity(Intent(requireContext(), BottomSheetOrderCartActivity::class.java).apply {
                putExtra(TOTAL_AMOUNT, totalAmount)
                putExtra(LIST_NAME_DATACART, listNameDataCart)
            })
        }
    }
}
