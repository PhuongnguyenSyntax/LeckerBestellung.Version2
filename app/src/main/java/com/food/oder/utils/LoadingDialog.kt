package com.food.oder.utils

import android.app.Activity
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.food.oder.databinding.DialogLoadingBinding

class LoadingDialog(private val activity: Activity) {
    private var alertDialogLoading: AlertDialog? = null

    fun showDialog() {
        if (!activity.isFinishing && alertDialogLoading == null) {
            val builder = AlertDialog.Builder(activity)
            val inflater = LayoutInflater.from(activity)
            val binding = DialogLoadingBinding.inflate(inflater)
            builder.setView(binding.root)
            builder.setCancelable(false)

            alertDialogLoading = builder.create()
            alertDialogLoading?.show()
        }
    }

    fun dismissDialog() {
        if (!activity.isFinishing && alertDialogLoading?.isShowing == true) {
            alertDialogLoading?.dismiss()
        }
    }
}