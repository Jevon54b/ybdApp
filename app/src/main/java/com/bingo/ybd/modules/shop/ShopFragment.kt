package com.bingo.ybd.modules.shop

import com.bingo.ybd.R
import com.bingo.ybd.base.fragment.BaseVMFragment
import com.bingo.ybd.base.viewmodel.BaseViewModel

class ShopFragment: BaseVMFragment() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_shop
    }

    override fun getViewModel(): BaseViewModel {
        return BaseViewModel()
    }

    override fun initView() {

    }
}