package com.bingo.ybd.modules.disc.activity

import com.bingo.ybd.R
import com.bingo.ybd.base.activity.BaseVMActivity
import com.bingo.ybd.base.viewmodel.BaseViewModel

class ArticleListActivity:BaseVMActivity() {
    override fun getLayoutId(): Int {
        return R.layout.item_article
    }

    override fun initView() {

    }

    override fun getViewModel(): BaseViewModel {
        return BaseViewModel()
    }
}