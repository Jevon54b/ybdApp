package com.bingo.ybd.modules.shop

import com.bingo.ybd.R
import com.bingo.ybd.base.activity.BaseVMActivity
import com.bingo.ybd.base.viewmodel.BaseViewModel

class PostOrderActivity : BaseVMActivity(){
    override fun getLayoutId(): Int {
        return R.layout.activity_post_order
    }

    override fun initView() {

    }

    override fun getViewModel(): BaseViewModel {
        return BaseViewModel()
    }

}