package com.food.oder.ui.component.activity.main

import androidx.viewpager2.widget.ViewPager2
import com.food.oder.R
import com.food.oder.databinding.ActivityMainBinding
import com.food.oder.ui.adapter.ViewPagerMainAdapter
import com.food.oder.ui.bases.BaseActivity
import com.food.oder.ui.component.fragment.CartFragment
import com.food.oder.ui.component.fragment.ContactFragment
import com.food.oder.ui.component.fragment.FeedBackFragment
import com.food.oder.ui.component.fragment.HistoryFragment
import com.food.oder.ui.component.fragment.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var viewPagerMainAdapter: ViewPagerMainAdapter

    override fun getLayoutActivity() = R.layout.activity_main

    override fun initViews() {
        super.initViews()

        setUpViewPager()
        setUpNavigation()
    }

    private fun setUpViewPager() {
        if (!this@MainActivity.supportFragmentManager.isDestroyed) {
            viewPagerMainAdapter =
                ViewPagerMainAdapter(this@MainActivity.supportFragmentManager, lifecycle)
            viewPagerMainAdapter.addFragment(HomeFragment())
            viewPagerMainAdapter.addFragment(CartFragment())
            viewPagerMainAdapter.addFragment(FeedBackFragment())
            viewPagerMainAdapter.addFragment(ContactFragment())
            viewPagerMainAdapter.addFragment(HistoryFragment())

            mBinding.viewpager.apply {
                adapter = viewPagerMainAdapter
                offscreenPageLimit = 5
            }

            mBinding.viewpager.apply {
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        when(position){
                            0 -> {
                                mBinding.bottomNavigation.menu.findItem(R.id.nav_home).isChecked = true
                            }

                            1 -> {
                                mBinding.bottomNavigation.menu.findItem(R.id.nav_cart).isChecked = true
                            }

                            2 -> {
                                mBinding.bottomNavigation.menu.findItem(R.id.nav_feedback).isChecked = true
                            }

                            3 -> {
                                mBinding.bottomNavigation.menu.findItem(R.id.nav_contact).isChecked = true
                            }

                            4 -> {
                                mBinding.bottomNavigation.menu.findItem(R.id.nav_order).isChecked = true
                            }
                        }
                    }
                })
            }
        }
    }

    private fun setUpNavigation() {
        mBinding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> {
                    mBinding.viewpager.currentItem = 0
                }

                R.id.nav_cart -> {
                    mBinding.viewpager.currentItem = 1
                }

                R.id.nav_feedback -> {
                    mBinding.viewpager.currentItem = 2
                }

                R.id.nav_contact -> {
                    mBinding.viewpager.currentItem = 3
                }

                R.id.nav_order -> {
                    mBinding.viewpager.currentItem = 4
                }
            }

            true
        }
    }

}
