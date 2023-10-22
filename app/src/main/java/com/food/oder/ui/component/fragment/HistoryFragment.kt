package com.food.oder.ui.component.fragment

import android.view.View
import com.food.oder.R
import com.food.oder.databinding.FragmentOrderBinding
import com.food.oder.ui.bases.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentOrderBinding>(){

    override fun getLayoutFragment() = R.layout.fragment_order

    override fun initViews() {
        super.initViews()

        mBinding.toolbar.imvBack.visibility = View.GONE
        mBinding.toolbar.tvTitle.text = getString(R.string.order)
    }
}
