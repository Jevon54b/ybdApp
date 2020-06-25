package com.bingo.ybd.modules.mine.fragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.bingo.ybd.R
import com.bingo.ybd.base.fragment.BaseVMFragment
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.config.Settings
import com.bingo.ybd.modules.main.vm.MainViewModel
import com.bingo.ybd.modules.mine.activity.OrderListActivity
import kotlinx.android.synthetic.main.fragment_mine.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MineFragment: BaseVMFragment() {

    companion object {
        const val TAG = "MineFragment"
    }

    private val mainViewModel: MainViewModel by viewModel()

    override fun getLayoutRes(): Int = R.layout.fragment_mine

    override fun getViewModel(): BaseViewModel = mainViewModel

    override fun initView() {
        myOrderView.setOnClickListener {
            var intent = Intent(requireContext(), OrderListActivity::class.java)
            startActivity(intent)
        }
    }

    override fun initData() {
        nickText.text = Settings.Account.userName
        userMoneyText.text = "${Settings.Account.userMoney}元"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e(TAG, "onAttach: ")
    }

}