package com.bingo.ybd.modules.mine.fragment


import com.bingo.ybd.R
import com.bingo.ybd.base.fragment.BaseVMFragment
import com.bingo.ybd.base.viewmodel.BaseViewModel

class MineFragment: BaseVMFragment(){
    override fun getLayoutRes(): Int {
        return R.layout.fragment_mine
    }

    override fun getViewModel(): BaseViewModel {
        return BaseViewModel()
    }

    override fun initView() {

    }
}