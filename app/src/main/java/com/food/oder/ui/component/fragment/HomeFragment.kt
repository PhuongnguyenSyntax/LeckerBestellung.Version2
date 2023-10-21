package com.food.oder.ui.component.fragment

import com.food.oder.R
import com.food.oder.databinding.FragmentHomeBinding
import com.food.oder.ui.bases.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(){

    override fun getLayoutFragment() = R.layout.fragment_home

}
