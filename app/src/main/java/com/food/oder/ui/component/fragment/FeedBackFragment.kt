package com.food.oder.ui.component.fragment

import com.food.oder.R
import com.food.oder.databinding.FragmentCartBinding
import com.food.oder.databinding.FragmentFeedbackBinding
import com.food.oder.databinding.FragmentHomeBinding
import com.food.oder.ui.bases.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedBackFragment : BaseFragment<FragmentFeedbackBinding>(){

    override fun getLayoutFragment() = R.layout.fragment_feedback

}
