package com.bingo.ybd.modules.main.activity

import android.content.res.ColorStateList
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.widget.TextView
import com.bingo.ybd.R
import com.bingo.ybd.base.activity.BaseVMActivity
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.modules.disc.fragment.DiscFragment
import com.bingo.ybd.modules.main.custom.FragPagerAdapter
import com.bingo.ybd.modules.main.fragment.HomeFragment
import com.bingo.ybd.modules.mine.fragment.MineFragment
import com.bingo.ybd.modules.shop.ShopFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseVMActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        supportActionBar?.title = "首页"
        initNavView()
        initViewPager()
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
        viewPager.setCurrentItem(0,false)
    }

    private fun initNavView(){
        navView.itemIconTintList = null
        var states = Array(2){IntArray(1){0}}
        states[0][0] = -android.R.attr.state_checked
        states[1][0] = android.R.attr.state_checked

        var colors = IntArray(2){0}
        colors[0]= R.color.colorPrimary
        colors[1] = R.color.colorWordBlack
        //设置选中颜色切换
        var csl = ColorStateList(states,colors)
        navView.itemTextColor = csl
        //切换监听
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    override fun getViewModel(): BaseViewModel {
        return BaseViewModel()
    }

    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                supportActionBar?.title = "首页"
                viewPager.setCurrentItem(0,false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_disc -> {
                supportActionBar?.title = "发现"
                viewPager.setCurrentItem(1,false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_shop -> {
                supportActionBar?.title = "购物车"
                viewPager.setCurrentItem(2,false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mine -> {
                supportActionBar?.title = "我的"
                viewPager.setCurrentItem(3,false)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


}
