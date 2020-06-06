package com.bingo.ybd.modules.user.activity

import com.bingo.ybd.R
import com.bingo.ybd.base.activity.BaseVMActivity
import com.bingo.ybd.base.viewmodel.BaseViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity:BaseVMActivity(){
    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initView() {
        loginText.setOnClickListener {
            finish()
        }
    }

    override fun getViewModel(): BaseViewModel {
        return BaseViewModel()
    }
}