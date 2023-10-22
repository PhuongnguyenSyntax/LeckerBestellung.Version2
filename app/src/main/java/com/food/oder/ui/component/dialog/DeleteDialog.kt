package com.food.oder.ui.component.dialog

import android.content.Context
import com.food.oder.R
import com.food.oder.databinding.DialogDeleteCartBinding
import com.food.oder.ui.bases.BaseDialog

class DeleteDialog(
    context: Context,
    val onclickDelete: () -> Unit
) : BaseDialog<DialogDeleteCartBinding>(context) {

    override fun getLayoutDialog() = R.layout.dialog_delete_cart

    override fun onClickViews() {
        super.onClickViews()
        mBinding.tvOk.setOnClickListener {
            onclickDelete()
            dismiss()
        }
        mBinding.tvCancel.setOnClickListener {
            dismiss()
        }
    }
}