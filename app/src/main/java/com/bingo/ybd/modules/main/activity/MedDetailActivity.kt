package com.bingo.ybd.modules.main.activity

import com.bingo.ybd.R
import com.bingo.ybd.base.activity.BaseVMActivity
import com.bingo.ybd.base.viewmodel.BaseViewModel

class MedDetailActivity:BaseVMActivity() {
    override fun getLayoutId(): Int = R.layout.activity_med_detail

    override fun initView() {

    }

    override fun initData() {

    }

    override fun getViewModel(): BaseViewModel {
        return BaseViewModel()
    }
}