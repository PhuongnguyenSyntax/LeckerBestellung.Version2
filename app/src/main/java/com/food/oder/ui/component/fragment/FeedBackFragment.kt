package com.food.oder.ui.component.fragment

import android.view.View
import android.widget.Toast
import com.food.oder.R
import com.food.oder.data.model.FeedBack
import com.food.oder.databinding.FragmentCartBinding
import com.food.oder.databinding.FragmentFeedbackBinding
import com.food.oder.databinding.FragmentHomeBinding
import com.food.oder.ui.bases.BaseFragment
import com.food.oder.utils.tap
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedBackFragment : BaseFragment<FragmentFeedbackBinding>() {
    private val database = Firebase.database

    override fun getLayoutFragment() = R.layout.fragment_feedback

    override fun initViews() {
        super.initViews()

        mBinding.toolbar.imvBack.visibility = View.GONE
        mBinding.toolbar.tvTitle.text = getString(R.string.feedback)
    }

    override fun onClickViews() {
        super.onClickViews()

        mBinding.tvSendFeedback.tap {
            if (mBinding.edtName.text.trim().isNotEmpty() && mBinding.edtPhone.text.trim()
                    .isNotEmpty() && mBinding.edtEmail.text.trim()
                    .isNotEmpty() && mBinding.edtComment.text.trim()
                    .isNotEmpty()
            ) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.please_wait),
                    Toast.LENGTH_SHORT
                ).show()
                val myRef = database.getReference("feedBack").push()
                myRef.setValue(
                    FeedBack(
                        name = mBinding.edtName.text.trim().toString(),
                        numberPhone = mBinding.edtPhone.text.trim().toString(),
                        email = mBinding.edtEmail.text.trim().toString(),
                        feedBack = mBinding.edtComment.text.trim().toString()
                    )
                ).addOnSuccessListener {
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
            }else{
                Toast.makeText(requireContext(), getString(R.string.value_not_emty), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
