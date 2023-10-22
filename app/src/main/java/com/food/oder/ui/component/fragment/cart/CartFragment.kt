package com.food.oder.ui.component.fragment.cart

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.food.oder.R
import com.food.oder.data.liveData.StateData
import com.food.oder.data.model.Cart
import com.food.oder.databinding.FragmentCartBinding
import com.food.oder.ui.adapter.CartAdapter
import com.food.oder.ui.bases.BaseFragment
import com.food.oder.ui.component.dialog.DeleteDialog
import com.food.oder.ui.component.fragment.cart.viewModel.CartViewModel
import com.food.oder.utils.LoadingDialog
import com.food.oder.utils.tap
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>() {
    private val database = Firebase.database

    private val cartViewModel: CartViewModel by viewModels()

    private var cartAdapter: CartAdapter? = null

    private var totalAmount = 0

    private var listDataCart: List<Cart>? = null

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
                        if (listDataCart != null) {

                            this.listDataCart = listDataCart

                            cartAdapter?.submitData(listDataCart)
                            if (listDataCart.isNotEmpty()) {
                                mBinding.rcvFoodCart.visibility = View.VISIBLE
                                mBinding.layoutBottom.visibility = View.VISIBLE
                                mBinding.tvNoItem.visibility = View.GONE

                                for (i in 0..listDataCart.lastIndex) {
                                    totalAmount += (listDataCart[i].amount * listDataCart[i].price.toInt())
                                }

                                mBinding.tvTotalPrice.text = totalAmount.toString()
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
            Toast.makeText(
                requireContext(),
                getString(R.string.please_wait),
                Toast.LENGTH_SHORT
            ).show()
            val myRef = database.getReference("cart").push()
            myRef.setValue(this.listDataCart).addOnSuccessListener {
                cartViewModel.deleteAllDataCart()
                Toast.makeText(
                    requireContext(),
                    getString(R.string.msg_order_success),
                    Toast.LENGTH_SHORT
                ).show()
            }.addOnFailureListener {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.error_not_connect_to_internet),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
