package com.bingo.ybd.modules.mine.fragment


import android.content.Intent
import com.bingo.ybd.R
import com.bingo.ybd.base.fragment.BaseVMFragment
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.modules.mine.activity.OrderListActivity
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment: BaseVMFragment(){
    override fun getLayoutRes(): Int {
        return R.layout.fragment_mine
    }

    override fun getViewModel(): BaseViewModel {
        return BaseViewModel()
    }

    override fun initView() {
        myOrderView.setOnClickListener {
            var intent = Intent(requireContext(),OrderListActivity::class.java)
            startActivity(intent)
        }
    }
}