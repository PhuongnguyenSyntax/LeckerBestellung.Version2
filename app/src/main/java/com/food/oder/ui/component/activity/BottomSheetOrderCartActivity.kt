package com.food.oder.ui.component.activity

import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.food.oder.R
import com.food.oder.app.AppConstants.LIST_NAME_DATACART
import com.food.oder.app.AppConstants.TOTAL_AMOUNT
import com.food.oder.data.model.CartOrder
import com.food.oder.databinding.LayoutBottomSheetOrderBinding
import com.food.oder.ui.bases.BaseActivity
import com.food.oder.ui.component.fragment.cart.viewModel.CartViewModel
import com.food.oder.utils.tap
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date

@AndroidEntryPoint
class BottomSheetOrderCartActivity : BaseActivity<LayoutBottomSheetOrderBinding>() {
    private val cartViewModel: CartViewModel by viewModels()

    private val database = Firebase.database

    private var menuList = arrayListOf<String>()
    private var totalAmount = 0

    override fun getLayoutActivity() = R.layout.layout_bottom_sheet_order

    override fun initViews() {
        super.initViews()

        intent.getStringArrayListExtra(LIST_NAME_DATACART)?.let {
            menuList.addAll(
                it
            )
        }

        totalAmount = intent.getIntExtra(TOTAL_AMOUNT, 0)
    }

    override fun onClickViews() {
        super.onClickViews()

        mBinding.tvCancelOrder.tap {
            finish()
        }

        mBinding.tvCreateOrder.tap {
            if (mBinding.edtNameOrder.text.trim().isNotEmpty() && mBinding.edtPhoneOrder.text.trim()
                    .isNotEmpty() && mBinding.edtAddressOrder.text.trim().isNotEmpty()
            ) {
                val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
                val currentDate = sdf.format(Date())

                Toast.makeText(
                    this,
                    getString(R.string.please_wait),
                    Toast.LENGTH_SHORT
                ).show()
                val myRef = database.getReference("cart").push()
                myRef.setValue(
                    CartOrder(
                        fullName = mBinding.edtNameOrder.text.trim().toString(),
                        numberPhone = mBinding.edtPhoneOrder.text.trim().toString(),
                        address = mBinding.edtAddressOrder.text.trim().toString(),
                        menu = menuList,
                        date = currentDate,
                        totalAmount = totalAmount
                    )
                ).addOnSuccessListener {
                    cartViewModel.deleteAllDataCart()
                    Toast.makeText(
                        this,
                        getString(R.string.msg_order_success),
                        Toast.LENGTH_SHORT
                    ).show()

                    mBinding.edtNameOrder.setText("")
                    mBinding.edtPhoneOrder.setText("")
                    mBinding.edtAddressOrder.setText("")

                    finish()
                }.addOnFailureListener {
                    Toast.makeText(
                        this,
                        getString(R.string.error_not_connect_to_internet),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(this, getString(R.string.value_not_emty), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
