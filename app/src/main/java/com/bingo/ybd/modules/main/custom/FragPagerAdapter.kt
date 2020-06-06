package com.bingo.ybd.modules.main.custom

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragPagerAdapter(fragmentActivity:FragmentActivity) : FragmentStateAdapter(fragmentActivity){

    private val mFragments = ArrayList<Fragment>()

    fun addFragment(fragment:Fragment){
        mFragments.add(fragment)
    }

    override fun getItemCount(): Int {
        return mFragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return mFragments[position]
    }
}