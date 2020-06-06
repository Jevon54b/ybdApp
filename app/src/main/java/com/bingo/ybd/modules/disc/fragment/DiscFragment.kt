package com.bingo.ybd.modules.disc.fragment


import com.bingo.ybd.R
import com.bingo.ybd.base.fragment.BaseVMFragment
import com.bingo.ybd.base.viewmodel.BaseViewModel

class DiscFragment: BaseVMFragment() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_disc
    }

    override fun getViewModel(): BaseViewModel {
        return BaseViewModel()
    }

    override fun initView() {

    }
}