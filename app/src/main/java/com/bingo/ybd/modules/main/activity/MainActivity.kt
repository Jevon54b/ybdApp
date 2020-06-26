package com.bingo.ybd.modules.main.activity

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.widget.TextView
import androidx.core.view.get
import com.bingo.ybd.R
import com.bingo.ybd.base.activity.BaseVMActivity
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.constant.Constant
import com.bingo.ybd.data.model.UserInfo
import com.bingo.ybd.modules.disc.fragment.DiscFragment
import com.bingo.ybd.modules.main.custom.FragPagerAdapter
import com.bingo.ybd.modules.main.fragment.HomeFragment
import com.bingo.ybd.modules.main.vm.MainViewModel
import com.bingo.ybd.modules.mine.fragment.MineFragment
import com.bingo.ybd.modules.shop.ShopFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseVMActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    val mainViewModel: MainViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModel(): BaseViewModel = mainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initView() {
        initNavView()
        initViewPager()
    }

    override fun initData() {

    }

    private fun initViewPager() {
        val pagerAdapter = FragPagerAdapter(this)
        pagerAdapter.addFragment(HomeFragment())
        pagerAdapter.addFragment(DiscFragment())
        pagerAdapter.addFragment(ShopFragment())
        pagerAdapter.addFragment(MineFragment())
        viewPager.adapter = pagerAdapter
        viewPager.offscreenPageLimit = pagerAdapter.itemCount
        viewPager.isUserInputEnabled = false
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val pageIndex = intent.getIntExtra(Constant.KEY_CART_PAGE_INDEX, 0)
        viewPager.setCurrentItem(pageIndex, false)
        navView.menu.getItem(2).isChecked = true
    }

    private fun initNavView() {
        navView.itemIconTintList = null
        var states = Array(2) { IntArray(1) { 0 } }
        states[0][0] = -android.R.attr.state_checked
        states[1][0] = android.R.attr.state_checked

        var colors = IntArray(2) { 0 }
        colors[0] = R.color.colorPrimary
        colors[1] = R.color.colorWordBlack
        //设置选中颜色切换
        var csl = ColorStateList(states,colors)
        navView.itemTextColor = csl
        //切换监听
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }


    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                viewPager.setCurrentItem(0,false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_disc -> {
                viewPager.setCurrentItem(1,false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_shop -> {
                viewPager.setCurrentItem(2,false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mine -> {
                viewPager.setCurrentItem(3,false)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


}
